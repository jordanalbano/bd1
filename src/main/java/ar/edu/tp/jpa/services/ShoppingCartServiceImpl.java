package ar.edu.tp.jpa.services;

import ar.edu.tp.api.ShoppingCartService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final EntityManagerFactory emf;

    public ShoppingCartServiceImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public BigDecimal totalPriceById(String id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            return em.createQuery("SELECT SUM(p.price) FROM ShoppingCart s JOIN s.products p WHERE s.id = :id", BigDecimal.class)
                    .setParameter("id", id)
                    .getSingleResult();
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
