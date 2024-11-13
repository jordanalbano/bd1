package ar.edu.tp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Sale {
    @UuidGenerator
    @Id
    private String id;
    private LocalDateTime createdOn;
    @ManyToOne
    private Client client;
    @OneToOne
    private ShoppingCart shoppingCart;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private BigDecimal totalPrice;
    private  String uniqueNumber;

    public Sale(Client client,
                ShoppingCart shoppingCart,
                PaymentMethod paymentMethod, String uniqueNumber) {
        this.client = client;
        this.shoppingCart = shoppingCart;
        this.paymentMethod = paymentMethod;
        this.uniqueNumber = uniqueNumber;

    }

    public Sale() {
    }

    public Sale(String uniqueNumber) {

        this.uniqueNumber = uniqueNumber;
    }

    public void validate() {
        if (Objects.isNull(client)) {
            throw new RuntimeException("El cliente no puede ser nulo");
        }
        if (Objects.isNull(shoppingCart)) {
            throw new RuntimeException("El carrito de compras no puede ser nulo");
        }

        if (Objects.isNull(paymentMethod)) {
            throw new RuntimeException("El método de pago no puede ser nulo");
        }

        this.shoppingCart.validate();
    }

    public Client client() {
        return client;
    }

    public ShoppingCart shoppingCart() {
        return shoppingCart;
    }

    public void init() {
        this.createdOn = LocalDateTime.now();
        this.totalPrice = this.shoppingCart.calculateTotal();

    }

    public void nextNumber(String s) {
        this.uniqueNumber = s;
    }
}
