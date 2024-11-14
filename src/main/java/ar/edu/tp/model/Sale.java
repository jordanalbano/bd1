package ar.edu.tp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Sale implements Serializable {
    @UuidGenerator
    @Id
    private String id;
    private LocalDate createdOn;
    @ManyToOne
    private Client client;
    @OneToOne
    private ShoppingCart shoppingCart;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private BigDecimal totalPrice;
    private String uniqueNumber;
    @ManyToMany
    private List<CreditCartDiscount> discounts;

    public Sale(Client client,
                ShoppingCart shoppingCart,
                PaymentMethod paymentMethod,
                String uniqueNumber) {
        this.client = client;
        this.shoppingCart = shoppingCart;
        this.paymentMethod = paymentMethod;
        this.uniqueNumber = uniqueNumber;

    }

    public Sale() {
        this.discounts = List.of();
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
        this.createdOn = LocalDate.now();

        this.totalPrice = this.shoppingCart.calculateTotal();
        if (!this.discounts.isEmpty()) {
            var discountAvailable = this.discounts.stream().filter(CreditCartDiscount::isActive).findFirst();
            discountAvailable.ifPresent(creditCartDiscount -> this.totalPrice = creditCartDiscount.calculateDiscountedPrice(this.totalPrice));
        }
    }

    public void nextNumber(String s) {
        this.uniqueNumber = s;
    }

    public void assignShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
