package ar.edu.tp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
@Getter
@Setter
@Entity
public class ItemProduct {
    @UuidGenerator
    @Id
    private String id;
    private int quantity;
    private  String code;
    private  BigDecimal price;
    @ManyToOne
    private  Product product;
    @ManyToOne
    private  ShoppingCart shoppingCart;
    public ItemProduct(Product product, int quantity, String code, ShoppingCart shoppingCart) {
        this.quantity = quantity;
        this.code = code;
        this.product = product;
        this.shoppingCart = shoppingCart;
        this.price = product.price().multiply(BigDecimal.valueOf(quantity));
    }

    public ItemProduct() {

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
