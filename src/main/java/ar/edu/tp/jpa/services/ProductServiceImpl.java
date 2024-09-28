package ar.edu.tp.jpa.services;

import ar.edu.tp.api.ProductService;
import ar.edu.tp.model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public void create(String code, String description, float price, Long categoryId) {

    }

    @Override
    public void update(String productId, String code, String description, float price, Long categoryId) {

    }

    @Override
    public List<Product> all() {
        return List.of();
    }
}
