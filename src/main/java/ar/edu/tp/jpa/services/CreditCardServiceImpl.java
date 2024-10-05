package ar.edu.tp.jpa.services;

import ar.edu.tp.api.CreditCardService;
import ar.edu.tp.model.Client;
import ar.edu.tp.model.CreditCard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final EntityManagerFactory emf;

    public CreditCardServiceImpl() {
        this.emf = Persistence.createEntityManagerFactory("jpa-mysql");
    }
    @Override
    public Collection<CreditCard> findAllByClientId(String id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            var query = em.createQuery("SELECT c FROM CreditCard c WHERE c.client.id = :id", CreditCard.class);
            query.setParameter("id", id);
            List<CreditCard> creditCards = query.getResultList();
            tx.commit();
            return creditCards;
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em.isOpen())
                em.close();
            emf.close();
        }
    }
}
