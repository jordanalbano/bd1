package ar.edu.tp.jpa.services;

import ar.edu.tp.api.SaleService;
import ar.edu.tp.model.*;
import exceptions.BadRequestException;
import exceptions.EntityNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class SaleServiceImpl implements SaleService {
    private final EntityManagerFactory emf;

    public SaleServiceImpl() {
        this.emf = Persistence.createEntityManagerFactory("jpa-objectdb");

    }

    @Override
    public void makeSale(Long idCliente, List<Long> products, Long cardId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            var client = Optional.ofNullable(em.find(Client.class, idCliente));
            if (client.isEmpty()) {
                throw new EntityNotFoundException("La lista de productos no puede estar vacía");
            }
            var card = em.find(CreditCard.class, cardId);
            if (card == null) {
                throw new EntityNotFoundException("La tarjeta no existe");
            }
            var shoppingCart = new ShoppingCart(client.get());
            var productsList = em.createQuery("SELECT p " +
                            "FROM Product p WHERE p.id IN :products", Product.class)
                    .setParameter("products", products)
                    .getResultList();
            shoppingCart.addProductItemByProduct(productsList);

            if (client.get().cardBelongs(card)) {
                throw new BadRequestException("La tarjeta no pertenece al cliente");
            }
            var sale = new Sale(client.get(), shoppingCart, PaymentMethod.CARD);
            em.persist(sale);

        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em.isOpen())
                em.close();
            emf.close();
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
}
