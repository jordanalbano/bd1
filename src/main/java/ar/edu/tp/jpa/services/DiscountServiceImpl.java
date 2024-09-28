package ar.edu.tp.jpa.services;

import ar.edu.tp.api.DiscountService;
import ar.edu.tp.model.Brand;
import ar.edu.tp.model.BrandDiscount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class DiscountServiceImpl implements DiscountService {
    private final EntityManagerFactory emf;

    public DiscountServiceImpl() {
        this.emf = Persistence.createEntityManagerFactory("jpa-objectdb");

    }

    @Override
    public void createDiscountOnTotal(String cardBrand, LocalDate startDate, LocalDate endDate, float percentage) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            var discount = new BrandDiscount(new Brand(cardBrand), startDate, endDate, (int) percentage);
            em.persist(discount);

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
    public void create(String cardBrand, LocalDate startDate, LocalDate endDate, float percentage) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            var discount = new BrandDiscount(new Brand(cardBrand), startDate, endDate, (int) percentage);
            em.persist(discount);

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
