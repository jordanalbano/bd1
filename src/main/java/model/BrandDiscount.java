package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class BrandDiscount extends Discount {
    private final Brand brand;

    public BrandDiscount(Brand brand, LocalDate startDate, LocalDate endDate, int discountPercentage) {
        super(startDate, endDate, discountPercentage);
        this.brand = brand;
    }

    public BigDecimal calculateDiscountedPrice(BigDecimal priceProduct) {
        if (!discountByBrandAvailable(brand)) {
            return priceProduct;
        }
        var Discount = new BigDecimal(this.discountPercentage);
        var hundred = new BigDecimal(100);
        var discount = Discount.divide(hundred, 2, RoundingMode.HALF_UP);
        return priceProduct.multiply(discount);

    }

    private boolean discountByBrandAvailable(Brand brand) {
        var today = LocalDate.now();
        return this.brand.equals(brand) && this.endDate.isAfter(today) && this.startDate.isBefore(today)  ;
    }

    public boolean isActive() {
        var today = LocalDate.now();
        return this.endDate.isAfter(today) && this.startDate.isBefore(today);
    }

    public Brand brand() {
        return this.brand;
    }

    public boolean applicableToBrand(Brand brand) {
        return this.brand.equals(brand);
    }
}
