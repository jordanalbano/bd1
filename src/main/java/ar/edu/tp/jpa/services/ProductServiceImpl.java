package ar.edu.tp.jpa.services;

import ar.edu.tp.api.ProductService;
import ar.edu.tp.model.Category;
import ar.edu.tp.model.Client;
import ar.edu.tp.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final EntityManagerFactory emf;

    public ProductServiceImpl() {
        this.emf = Persistence.createEntityManagerFactory("jpa-objectdb");
    }

    @Override
    public void create(String code, String description, BigDecimal price, Long categoryId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            var category = em.find(Category.class, categoryId);
            var product = new Product(code, description, price, category);

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
    public void update(String productId, String code, String description, BigDecimal price, Long categoryId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            var product = Optional.ofNullable(em.find(Product.class, productId));
            var category = Optional.ofNullable(em.find(Category.class, categoryId));
            if (product.isEmpty()) {
                throw new IllegalArgumentException("El producto no existe");
            }
            if (category.isEmpty()) {
                throw new IllegalArgumentException("La categoría no existe");
            }
            var newProduct = new Product(code, description, price, category.get());
            product.get().update(newProduct);
            em.merge(product);
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
    public List<Product> all() {
        return List.of();
    }
}
