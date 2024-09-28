package ar.edu.tp.api;

import ar.edu.tp.model.Sale;

import java.util.List;

public interface SaleService {
    //Crea una venta. El monto se calcula aplicando los descuentos a la fecha
    // validaciones:
    // - debe ser un cliente existente
    // - la lista de productos no debe estar vacía
    // - La tarjeta debe pertenecer al cliente
    void makeSale(Long idCliente, List<Long> products, Long cardId);

    //Devuelve el monto total aplicando los descuentos al día de la fecha
    // validar que no llegue una lista vacía y la tarjeta exista
    float calculatePrice(List<Long> products, Long cardId);

    //Devuelve todas las ventas realizadas
    List<Sale> ventas();
}
