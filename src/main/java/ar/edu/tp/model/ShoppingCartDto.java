package ar.edu.tp.model;

import ar.edu.tp.dto.ClientDto;
import ar.edu.tp.dto.ItemProductDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for {@link ShoppingCart}
 */
public record ShoppingCartDto(String id, ClientDto client, Set<ItemProductDto> productItems,
                              BigDecimal total) implements Serializable {
}