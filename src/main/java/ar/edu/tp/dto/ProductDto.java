package ar.edu.tp.dto;

import ar.edu.tp.model.Category;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for {@link ar.edu.tp.model.Product}
 */
public record ProductDto(String id, String code, String description, Category category, BigDecimal price,
                         List<BrandDiscountDto> discounts, BrandDTO brand, Long version) implements Serializable {
}