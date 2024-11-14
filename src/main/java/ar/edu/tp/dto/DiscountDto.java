package ar.edu.tp.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link ar.edu.tp.model.Discount}
 */
public record DiscountDto(String id, LocalDate startDate, LocalDate endDate,
                          int discountPercentage) implements Serializable {
}