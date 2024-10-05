package ar.edu.tp.api;

import java.math.BigDecimal;

public interface ShoppingCartService {
    BigDecimal totalPriceById(String id);
}
