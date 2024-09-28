package ar.edu.tp.api;

import ar.edu.tp.model.CreditCard;

import java.util.List;

public interface ClientService {
    // validar que el dni no se repita
    void create(String name, String lastname, String dni, String email);

    // validar que sea un cliente existente
    void update(Long idCliente, String nombre, ...);

    // validar que sea un cliente existente
    void addCard(Long idCliente, String nro, String marca);

    //Devuelve las tarjetas de un cliente específico
    List<CreditCard> listCard(Long idCliente);
}
