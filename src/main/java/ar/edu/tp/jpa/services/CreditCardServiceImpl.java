package ar.edu.tp.jpa.services;

import ar.edu.tp.api.CreditCardService;
import ar.edu.tp.model.Client;
import ar.edu.tp.model.CreditCard;
import ar.edu.tp.model.CreditCardProvider;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final EntityManagerFactory emf;

    public CreditCardServiceImpl() {
        this.emf = Persistence.createEntityManagerFactory("jpa-objectdb");
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
