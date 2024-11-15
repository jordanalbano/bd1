package ar.edu.tp.model;

import ar.edu.tp.dto.ClientDto;
import ar.edu.tp.dto.CreditCartDiscountDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Sale}
 */
public record SaleDto(String id, LocalDate createdOn, ClientDto client, ShoppingCartDto shoppingCart, PaymentMethod paymentMethod, BigDecimal totalPrice, String uniqueNumber, List<CreditCartDiscountDto> discounts) implements Serializable {
  }