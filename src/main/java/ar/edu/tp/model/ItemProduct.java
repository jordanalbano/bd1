package ar.edu.tp.model;

import java.math.BigDecimal;

public class ItemProduct {
    private int quantity;
    private final String code;
    private final BigDecimal price;
    private final Product product;
    public ItemProduct(Product product, int quantity, String code) {
        this.quantity = quantity;
        this.code = code;
        this.product = product;
        this.price = product.price().multiply(BigDecimal.valueOf(quantity));
    }
    public BigDecimal price() {
        return price;
    }

    public  int quantity() {
        return quantity;
    }
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public String code() {
        return code;
    }

    public Brand productBrand() {
        return this.product.brand();
    }
}
