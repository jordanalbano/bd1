package ar.edu.tp.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link ar.edu.tp.model.BrandDiscount}
 */
public record BrandDiscountDto (
        String id,
        LocalDate startDate,
        LocalDate endDate,
        int discountPercentage,
        BrandDTO brand

) {
    }