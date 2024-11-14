package ar.edu.tp.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link ar.edu.tp.model.ItemProduct}
 */
public record ItemProductDto(String id, int quantity, String code, BigDecimal price,
                             ProductDto product) implements Serializable {
}