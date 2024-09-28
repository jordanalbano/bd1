package ar.edu.tp.model;

import java.time.LocalDateTime;

public class Sale {
    LocalDateTime createdOn;
    private Client client;
    private ShoppingCart shoppingCart;
    private PaymentMethod paymentMethod;

    public Sale(Client client, ShoppingCart shoppingCart, PaymentMethod paymentMethod) {

    }
}
