package ar.edu.tp.dto;

import ar.edu.tp.model.CreditCard;

import java.io.Serializable;

/**
 * DTO for {@link CreditCard}
 */
public record CreditCardDto(String id, String number, String cvv, String yearExpiration, String monthExpiration,
                            CreditCardProviderDto creditCardProvider) implements Serializable {
}