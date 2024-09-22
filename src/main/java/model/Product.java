package model;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private String code;
    private String description;
    private Category category;
    private BigDecimal price;
    private List<Discount> discounts;
    private Brand brand;
    public Product(String code, String description, Category category, BigDecimal price, List<Discount> discounts, Brand brand) {
        this.validate(code, description, category, price, discounts, brand);
        this.code = code;
        this.description = description;
        this.category = category;
        this.price = price;
        this.discounts = discounts;
        this.brand = brand;
    }

    private void validate(String code, String description, Category category, BigDecimal price, List<Discount> discounts, Brand brand) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Code can't be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description can't be null or empty");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price can't be null or negative");
        }
        if (discounts == null) {
            throw new IllegalArgumentException("Discounts can't be null");
        }
        if (brand == null) {
            throw new IllegalArgumentException("Brand can't be null");
        }
    }

    public BigDecimal price() {
        return this.price;
    }

    public Brand brand() {
        return this.brand;
    }
}
