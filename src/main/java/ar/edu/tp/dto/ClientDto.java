package ar.edu.tp.dto;

import ar.edu.tp.model.Client;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Client}
 */
public record ClientDto(String id, String name, String lastname, String dni, String email, String phone,
                        List<CreditCardDto> creditCards) implements Serializable {
}