package ar.edu.tp.model;

import ar.edu.tp.exceptions.CreditCartDiscountAlreadyExistException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class Store {
    private final Set<Product> products;
    private final Set<Client> clients;
    private final Set<Brand> brands;
    private final List<CreditCartDiscount> creditCartDiscounts;
    private final List<BrandDiscount> brandDiscounts;
    private final Set<PaymentMethod> paymentMethods;
    private final Set<Sale> sales;

    public Store(Set<Product> products, Set<Client> clients, Set<Brand> brands, List<CreditCartDiscount> creditCartDiscounts, List<BrandDiscount> brandDiscounts, Set<PaymentMethod> paymentMethods, Set<Sale> sales) {
        this.products = products;
        this.clients = clients;
        this.brands = brands;
        this.creditCartDiscounts = creditCartDiscounts;
        this.brandDiscounts = brandDiscounts;
        this.paymentMethods = paymentMethods;
        this.sales = sales;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void addBrandDiscount(BrandDiscount discount) {
        if(this.brandDiscounts.stream().anyMatch(r -> r.brand().equals(discount.brand()) && r.isActive())) {
            throw new IllegalArgumentException("Brand discount already exist");
        }
        this.brandDiscounts.add(discount);
    }

    public void addCreditCardDiscount(CreditCartDiscount discount) {
        if (this.existsCreditCartDiscountActive(discount)) {
            throw new CreditCartDiscountAlreadyExistException();
        }
        this.creditCartDiscounts.add(discount);
    }

    private boolean existsCreditCartDiscountActive(CreditCartDiscount discount) {
        return this.creditCartDiscounts.stream().anyMatch(r -> r.isActiveByCreditCart(discount.creditCart()));
    }

    public void addBrand(Brand brand) {
        brands.add(brand);
    }

    public void addSale(Client client, ShoppingCart shoppingCart, PaymentMethod paymentMethod) {
        var sale = new Sale(client, shoppingCart, paymentMethod);
        this.sales.add(sale);
    }

    public BigDecimal calculateTotalAmountOfCart(ShoppingCart shoppingCart, CreditCardProvider creditCartProvider) {
        var totalAmountWithDiscountBran = shoppingCart.getProductItems().stream().map(r -> {
            var discountOptional = this.brandDiscounts.stream().filter(discount -> discount.isActive() && discount.applicableToBrand(r.productBrand())).findFirst();
            return discountOptional.map(brandDiscount -> r.price().subtract(brandDiscount.calculateDiscountedPrice(r.price()))).orElseGet(r::price);
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
        return calculateDiscountsForCreditCard(this.creditCartDiscounts, totalAmountWithDiscountBran, creditCartProvider);
    }


    private BigDecimal calculateDiscountsForCreditCard(List<CreditCartDiscount> creditCartDiscount, BigDecimal totalAmount, CreditCardProvider creditCartProvider) {
        var discount = creditCartDiscount.stream().filter(r -> r.isActive() && r.isActiveByCreditCart(creditCartProvider)).findFirst();
        if (discount.isPresent()) {
            return discount.get().calculateDiscountedPrice(totalAmount);
        }
        return totalAmount;
    }

    public BigDecimal calculateTotalAmountWithBrandDiscount(ShoppingCart shoppingCart, String brandName) {
        var totalAmount = BigDecimal.ZERO;
        var brandDiscount = this.brandDiscounts.stream()
                .filter(discount -> discount.isActive() && discount.brand().name().equalsIgnoreCase(brandName))
                .findFirst();

        for (ItemProduct product : shoppingCart.productItems()) {
            if (product.productBrand().name().equalsIgnoreCase(brandName) && brandDiscount.isPresent()) {
                totalAmount = totalAmount.add(product.price().subtract(brandDiscount.get().calculateDiscountedPrice(product.price())));
            } else {
                totalAmount = totalAmount.add(product.price());
            }
        }

        return totalAmount;
    }

}
