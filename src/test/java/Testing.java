import ar.edu.tp.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class Testing {


    //test to calculate total amount
    @org.junit.Test
    public void testCalculateTotalAmountOfCart() {
        var store = new Store(new HashSet<>(), new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new HashSet<>(), new HashSet<>());
        store.addBrandDiscount(new BrandDiscount(new Brand("arcor"), LocalDate.now().minusDays(5), LocalDate.now().minusDays(1), 10));
        store.addBrandDiscount(new BrandDiscount(new Brand("cepita"), LocalDate.now().minusDays(5), LocalDate.now().minusDays(1), 10));
        var product = new Product("code", "description",
                new Category("category"), new BigDecimal(100), new ArrayList<>(), new Brand("arcor"));
        store.addProduct(product);
        var client = new Client("name", "lastname", "dni", "email", "phone", new ArrayList<>());
        store.addClient(client);
        var creditCartProvider = new CreditCardProvider("provider");
        var creditCartDiscount = new CreditCartDiscount(LocalDate.now().minusDays(5), LocalDate.now().minusDays(1), creditCartProvider, 5);
        store.addCreditCardDiscount(creditCartDiscount);
        var shoppingCart = new ShoppingCart(client);
        shoppingCart.addProductItem(new ItemProduct(product, 2, "1", this));
        var totalAmount = store.calculateTotalAmountOfCart(shoppingCart, creditCartProvider);
        assertEquals(new BigDecimal(200), totalAmount);
    }

    /*
    *  Calcular el monto total del carrito con un descuento vigente para los
productos marca Acme.
*/
    @org.junit.Test
    public void testCalculateTotalAmountOfCartWithBrandDiscount() {
        var store = new Store(new HashSet<>(), new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new HashSet<>(), new HashSet<>());
        store.addBrandDiscount(new BrandDiscount(new Brand("Acme"), LocalDate.now().minusDays(5), LocalDate.now().plusDays(1), 10));
        var product = new Product("code", "description",
                new Category("category"), new BigDecimal(100), new ArrayList<>(), new Brand("Acme"));
        store.addProduct(product);
        var client = new Client("name", "lastname", "dni", "email", "phone", new ArrayList<>());
        store.addClient(client);
        var creditCartProvider = new CreditCardProvider("provider");
        var creditCartDiscount = new CreditCartDiscount(LocalDate.now().minusDays(5), LocalDate.now().minusDays(1), creditCartProvider, 5);
        store.addCreditCardDiscount(creditCartDiscount);
        var shoppingCart = new ShoppingCart(client);
        shoppingCart.addProductItem(new ItemProduct(product, 2, "1"));
        var totalAmount = store.calculateTotalAmountOfCart(shoppingCart, creditCartProvider);
        assertEquals(new BigDecimal("180.00"), totalAmount);

    }

    @org.junit.Test
    public void testCalculateTotalAmountOfCartWithCreditCardDiscount() {
        var store = new Store(new HashSet<>(), new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new HashSet<>(), new HashSet<>());
        var product = new Product("code", "description",
                new Category("category"), new BigDecimal(100), new ArrayList<>(), new Brand("Acme"));
        store.addProduct(product);
        var client = new Client("name", "lastname", "dni", "email", "phone", new ArrayList<>());
        store.addClient(client);
        var creditCartProvider = new CreditCardProvider("provider");
        var creditCartDiscount = new CreditCartDiscount(LocalDate.now().minusDays(5), LocalDate.now().plusDays(1), creditCartProvider, 50);
        store.addCreditCardDiscount(creditCartDiscount);
        var shoppingCart = new ShoppingCart(client);
        shoppingCart.addProductItem(new ItemProduct(product, 2, "1"));
        var totalAmount = store.calculateTotalAmountOfCart(shoppingCart, creditCartProvider);
        assertEquals(new BigDecimal("100.00"), totalAmount);

    }

    @org.junit.Test
    public void testCalculateTotalAmountOfCartWithTwoDiscounts() {
        var store = new Store(new HashSet<>(), new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new HashSet<>(), new HashSet<>());
        store.addBrandDiscount(new BrandDiscount(new Brand("Comarca"), LocalDate.now().minusDays(5), LocalDate.now().plusDays(1), 10));
        var product = new Product("code", "description",
                new Category("category"), new BigDecimal(100), new ArrayList<>(), new Brand("Comarca"));
        store.addProduct(product);
        var client = new Client("name", "lastname", "dni", "email", "phone", new ArrayList<>());
        store.addClient(client);
        var creditCartProvider = new CreditCardProvider("MemeCard");
        var creditCartDiscount = new CreditCartDiscount(LocalDate.now().minusDays(5), LocalDate.now().plusDays(1), creditCartProvider, 50);
        store.addCreditCardDiscount(creditCartDiscount);
        var shoppingCart = new ShoppingCart(client);
        shoppingCart.addProductItem(new ItemProduct(product, 2, "1"));
        var totalAmount = store.calculateTotalAmountOfCart(shoppingCart, creditCartProvider);
        assertEquals(new BigDecimal("90.00"), totalAmount);

    }

    //Verificar que no sea posible crear un Producto sin categoría,
    //descripción y precio
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testCreateProductWithoutCategory() {
        new Product("code", "description", null, new BigDecimal(100), new ArrayList<>(), new Brand("Comarca"));
    }

    //Verificar que no sea posible crear un Cliente sin dni, nombre y
    //apellido. Y que el email sea válido.
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testCreateClientWithoutDni() {
        new Client("name", "lastname", null, "email", "phone", new ArrayList<>());
    }

    // Verificar que no sea posible crear un descuento con fechas validez
    //superpuestas.
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testCreateDiscountWithOverlappingDates() {
        var store = new Store(new HashSet<>(), new HashSet<>(), new HashSet<>(), new ArrayList<>(), new ArrayList<>(), new HashSet<>(), new HashSet<>());
        store.addBrandDiscount(
                new BrandDiscount(new Brand("Comarca"), LocalDate.now().minusDays(5), LocalDate.now().plusDays(1), 10)
        );
        store.addBrandDiscount(
                new BrandDiscount(new Brand("Comarca"), LocalDate.now().minusDays(3), LocalDate.now().plusDays(5), 10)

        );

    }
}
