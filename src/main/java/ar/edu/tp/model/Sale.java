package ar.edu.tp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Sale {
    private LocalDateTime createdOn;
    private Client client;
    private ShoppingCart shoppingCart;
    private PaymentMethod paymentMethod;
    private BigDecimal totalPrice;
    public Sale(Client client,
                ShoppingCart shoppingCart,
                PaymentMethod paymentMethod) {

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
}
