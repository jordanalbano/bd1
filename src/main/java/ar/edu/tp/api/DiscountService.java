package ar.edu.tp.api;

import ar.edu.tp.model.Discount;
import ar.edu.tp.model.Product;

import java.time.LocalDate;
import java.util.Collection;

public interface DiscountService {
    // validar que las fechas no se superpongan
    void createDiscountOnTotal(String cardBrand, LocalDate startDate, LocalDate endDate, float percentage);

    // validar que las fechas no se superpongan
    void create(String cardBrand, LocalDate startDate, LocalDate endDate, float percentage);

    Collection<Discount> allCurrent();
}
