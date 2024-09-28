package ar.edu.tp.api;

import ar.edu.tp.model.CreditCard;
import ar.edu.tp.model.CreditCardProvider;

import java.util.List;

public interface ClientService {
    // validar que el dni no se repita
    void create(String name, String lastname, String dni, String email);

    // validar que sea un cliente existente
    void update(Long id, String name, String lastname, String dni, String email);

    // validar que sea un cliente existente
    void addCard(Long idCliente, String nro, String cvv, String yearExpiration, String monthExpiration, String creditCardProvider);

    //Devuelve las tarjetas de un cliente específico
    List<CreditCard> listCard(Long idCliente);
}
