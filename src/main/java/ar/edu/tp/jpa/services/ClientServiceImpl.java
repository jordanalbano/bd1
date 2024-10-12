package ar.edu.tp.jpa.services;

import ar.edu.tp.api.ClientService;
import ar.edu.tp.jpa.JpaClientRepository;
import ar.edu.tp.model.Client;
import ar.edu.tp.model.CreditCard;
import ar.edu.tp.model.CreditCardProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class ClientServiceImpl implements ClientService {
    private final EntityManagerFactory emf;

    public ClientServiceImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(String name, String lastname, String dni, String email) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            var client = new Client(name, lastname, dni, email);
            em.persist(client);
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
    public void update(Long id, String name, String lastname, String dni, String email) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            var client = em.find(Client.class, id);
            if (client == null) {
                throw new IllegalArgumentException("Client not found");
            }
            var newClient = new Client(name, lastname, dni, email);
            client.update(newClient);
            em.merge(client);
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
    public void addCard(Long idCliente, String nro, String cvv,
                        String yearExpiration,
                        String monthExpiration,
                        String creditCardProviderName) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            var client = em.find(Client.class, idCliente);
            if (client == null) {
                throw new IllegalArgumentException("Client not found");
            }
            var creditCardProvider = Optional.ofNullable(this.emf.createEntityManager().find(CreditCardProvider.class, creditCardProviderName));
            if (creditCardProvider.isEmpty()) {
                throw new IllegalArgumentException("El proveedor de tarjeta de crédito no existe");
            }
            var card = new CreditCard(nro, cvv, yearExpiration, monthExpiration, creditCardProvider.get());
            client.addCard(card);
            em.merge(client);
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
    public List<CreditCard> listCard(Long clientId) {
        EntityManager em = emf.createEntityManager();
        try {
            var client = Optional.ofNullable(em.find(Client.class, clientId));
            if (client.isEmpty()) {
                throw new IllegalArgumentException("Client not found");
            }
            return client.get().creditCards();
        } finally {
            if (em.isOpen())
                em.close();
            emf.close();
        }

    }
}
