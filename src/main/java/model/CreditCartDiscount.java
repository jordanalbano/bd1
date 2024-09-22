package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class CreditCartDiscount extends Discount {
    private final CreditCardProvider provider;
    private final boolean state;
    public CreditCartDiscount(LocalDate startDate, LocalDate endDate, CreditCardProvider provider, int discount) {
        super(startDate, endDate, discount);
        this.provider = provider;
        this.state = true;
    }

    public BigDecimal calculateDiscountedPrice(BigDecimal totalBuyPrice) {
        var percentageToDiscount = new BigDecimal(this.discountPercentage);
        var hundred = new BigDecimal(100);
        var discount = percentageToDiscount.divide(hundred, 2, RoundingMode.HALF_UP);
        return totalBuyPrice.multiply(discount).setScale(2, RoundingMode.HALF_UP);
    }
    public boolean isActive(){
        return this.state && this.endDate.isAfter(LocalDate.now()) && this.startDate.isBefore(LocalDate.now());
    }
    boolean discountByCreditCardAvailable() {
        var today = LocalDate.now();
        return this.endDate.isAfter(today) && this.startDate.isBefore(today);
    }

    public CreditCardProvider creditCart() {
        return this.provider;
    }

    public boolean isActiveByCreditCart(CreditCardProvider creditCardProvider) {
        return this.isActive() && this.provider.equals(creditCardProvider);
    }

    public long discountPercentage() {
        return this.discountPercentage;
    }
}
