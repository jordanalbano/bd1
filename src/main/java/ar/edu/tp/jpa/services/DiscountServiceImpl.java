package ar.edu.tp.jpa.services;

import ar.edu.tp.api.DiscountService;
import ar.edu.tp.model.Brand;
import ar.edu.tp.model.BrandDiscount;
import ar.edu.tp.model.Discount;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
@Service
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

    @Override
    public Collection<Discount> allCurrent() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            return em.createQuery("SELECT d FROM Discount d WHERE d.endDate >= :today", Discount.class)
                    .setParameter("today", LocalDate.now())
                    .getResultList();
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
