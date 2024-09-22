package model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {
    private Client client;
    Set<ItemProduct> productItems;

    public ShoppingCart(Client client) {
        this.client = client;
    }

    public BigDecimal calculateTotal() {
        return productItems.stream().map(ItemProduct::price).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public void addProductItem(ItemProduct itemProduct) {
        if (productItems == null) {
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
}
