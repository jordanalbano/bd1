package ar.edu.tp.api;

import ar.edu.tp.model.Product;

import java.util.List;

public interface ProductService {
    //validar que sea una categoría existente y que codigo no se repita
    void create(String code, String description, float price, Long
            categoryId);
    //validar que sea un producto existente
    void update(String productId, String code, String description, float price, Long
            categoryId);
    //Devuelve todos los productos
    List<Product> all();
}
