import ar.edu.tp.model.Brand;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("jpa-objectdb");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Brand brand = new Brand("Toyota");
            em.persist(brand);
//            em.persist(t);
//
//            // objectdb soporta entidades sin PK
//
//             em.persist(e);



        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em.isOpen())
                em.close();
            emf.close();
        }    }
}
