package ar.edu.tp.api;

import java.time.LocalDate;

public interface DiscountService {
    // validar que las fechas no se superpongan
    void createDiscountOnTotal(String cardBrand, LocalDate startDate, LocalDate endDate, float percentage);

    // validar que las fechas no se superpongan
    void create(String cardBrand, LocalDate startDate, LocalDate endDate, float percentage);

}
