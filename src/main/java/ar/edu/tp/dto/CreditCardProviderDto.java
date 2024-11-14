package ar.edu.tp.dto;

import ar.edu.tp.model.CreditCardProvider;

import java.io.Serializable;

/**
 * DTO for {@link CreditCardProvider}
 */
public record CreditCardProviderDto(String id, String name) implements Serializable {
}