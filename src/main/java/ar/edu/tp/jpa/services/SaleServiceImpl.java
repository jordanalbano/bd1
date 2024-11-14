package ar.edu.tp.jpa.services;

import ar.edu.tp.api.SaleService;
import ar.edu.tp.model.*;
import ar.edu.tp.exceptions.BadRequestException;
import ar.edu.tp.exceptions.EntityNotFoundException;
import ar.edu.tp.utils.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@Log4j2
public class SaleServiceImpl implements SaleService {
    private final EntityManagerFactory emf;
    private final Jedis jedis;
    private ObjectMapper objectMapper;
    private Mapper mapper;
    public SaleServiceImpl(EntityManagerFactory emf, ObjectMapper objectMapper, Mapper mapper) {
        this.objectMapper = objectMapper;
        this.mapper = mapper;
        this.jedis = new Jedis("localhost", 6379);
        this.emf = emf;
        this.objectMapper.findAndRegisterModules();
    }

    @Override
    public void makeSale(Long clientId, List<Long> products, Long cardId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            var client = Optional.ofNullable(em.find(Client.class, clientId));
            if (client.isEmpty()) {
                throw new EntityNotFoundException("La lista de productos no puede estar vacía");
            }
            var card = em.find(CreditCard.class, cardId);
            if (Objects.isNull(card)) {
                throw new EntityNotFoundException("La tarjeta no existe");
            }
            var shoppingCart = new ShoppingCart(client.get());
            var productsList = findAllProducts(em, products);
            shoppingCart.addProductItemByProduct(productsList);
            if (client.get().cardBelongs(card)) {
                throw new BadRequestException("La tarjeta no pertenece al cliente");
            }
            NextNumber uniqueNumber = findUniqueNumber(em);
            var sale = new Sale(client.get(), shoppingCart, PaymentMethod.CARD, uniqueNumber.recuperarSiguiente() + "-" + LocalDate.now().getYear());
            em.persist(sale);
            em.merge(uniqueNumber);
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em.isOpen())
                em.close();
            emf.close();
        }

    }

    private List<Product> findAllProducts(EntityManager em, List<Long> products) {
        return em.createQuery("SELECT p " +
                        "FROM Product p WHERE p.id IN :products", Product.class)
                .setParameter("products", products)
                .getResultList();
    }

    private NextNumber findUniqueNumber(EntityManager em) {
        Calendar calendar = Calendar.getInstance();
        int actualYear = calendar.get(Calendar.YEAR);
        try {
            TypedQuery<NextNumber> query = em.createQuery("select n from NextNumber n where year = :actualYear", NextNumber.class);
            query.setParameter("actualYear", actualYear);
            query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return new NextNumber(calendar.get(Calendar.YEAR), 1);
        }
    }

    @Override
    public float calculatePrice(List<Long> products, Long cardId) {
        return 0;
    }

    @Override
    public List<Sale> ventas() {
        return List.of();
    }

    @Override
    public void create(Sale sale) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            sale.validate();
            var clientInDB = Optional.ofNullable(em.find(Client.class, sale.client().id()));
            if (clientInDB.isEmpty()) {
                throw new EntityNotFoundException("El cliente no existe");
            }
            var shoppingCart = Optional.ofNullable(em.find(ShoppingCart.class, sale.shoppingCart().id()));
            if (shoppingCart.isEmpty()) {
                throw new EntityNotFoundException("El carrito de compras no existe");
            }
            shoppingCart.get().perform();
            NextNumber uniqueNumber = findUniqueNumber(em);
            sale.nextNumber(uniqueNumber.recuperarSiguiente() + "-" + LocalDate.now().getYear());
            sale.assignShoppingCart(shoppingCart.get());
            sale.init();
            em.persist(sale);
            em.merge(uniqueNumber);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleDto> getLastThreeSalesByClientId(String id) {
        var sales = this.getLastThreeSalesByClientIdInCache(id);
        if (!sales.isEmpty()) {
            return sales.stream().map(mapper::convert).toList();
        }
        EntityManager em = emf.createEntityManager();
        var client = Optional.ofNullable(em.find(Client.class, id));
        if (client.isEmpty()) {
            throw new EntityNotFoundException("El cliente no existe");
        }
        sales = em.createQuery("SELECT s FROM Sale s WHERE s.client = :client ORDER BY s.createdOn DESC", Sale.class)
                .setParameter("client", client.get())
                .setMaxResults(3)
                .getResultList();
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.findAndRegisterModules();
        this.jedis.set(id, this.convert(sales));
        return sales.stream().map(mapper::convert).toList();
    }

    private String convert(List<Sale> sales) {
        try {
            return this.objectMapper.writeValueAsString(sales);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public List<Sale> getLastThreeSalesByClientIdInCache(String id) {
        var res = Optional.ofNullable(this.jedis.get(id));
        return res.map(this::convert).orElse(List.of());
    }

    private List<Sale> convert(String s) {
        try {
            return List.of(this.objectMapper.readValue(s, Sale[].class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
