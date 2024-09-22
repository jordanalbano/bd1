package model;

import java.util.Set;

public class Discounts {
    private Set<Discount> discounts;

    public void add(Discount discount) {
        discounts.add(discount);
    }
}
