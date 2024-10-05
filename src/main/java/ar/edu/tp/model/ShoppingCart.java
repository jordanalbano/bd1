package ar.edu.tp.model;

import java.math.BigDecimal;
import java.util.*;

public class ShoppingCart {
    private final String id;
    private final Client client;
    Set<ItemProduct> productItems;

    public ShoppingCart(Client client) {
        this.id = UUID.randomUUID().toString();
        this.client = client;
    }

    public BigDecimal calculateTotal() {
        return productItems.stream().map(ItemProduct::price).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public void addProductItem(ItemProduct itemProduct) {
        if (Objects.isNull(productItems)) {
            productItems = new HashSet<>();
        }
        var productItem = productItems.stream().filter(r -> r.equals(itemProduct)).findFirst();
        if (productItem.isPresent()) {
            productItem.get().addQuantity(itemProduct.quantity());
            return;
        }
        productItems.add(itemProduct);
    }

    public Set<ItemProduct> productItems() {
        return this.productItems;
    }

    public void addProductItemByProduct(List<Product> products) {
        products.forEach(p -> addProductItem(new ItemProduct(p, 1, UUID.randomUUID().toString())));
    }

    public void validate() {
        if (Objects.isNull(client)) {
            throw new RuntimeException("El cliente no puede ser nulo");
        }
        if (Objects.isNull(productItems)) {
            throw new RuntimeException("El carrito de compras no puede ser nulo");
        }
    }

    public String id() {
        return id;
    }

}