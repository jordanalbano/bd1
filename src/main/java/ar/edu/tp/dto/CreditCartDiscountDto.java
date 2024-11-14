package ar.edu.tp.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link ar.edu.tp.model.CreditCartDiscount}
 */
public record CreditCartDiscountDto(String id, LocalDate startDate, LocalDate endDate, int discountPercentage,
                                    CreditCardProviderDto provider, boolean state) implements Serializable {
}