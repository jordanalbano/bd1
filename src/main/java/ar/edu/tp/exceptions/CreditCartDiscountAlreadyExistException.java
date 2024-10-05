package ar.edu.tp.exceptions;

public class CreditCartDiscountAlreadyExistException extends RuntimeException {
    public CreditCartDiscountAlreadyExistException() {
        super("Ya hay un descuento habilitado para esa tarjeta de credito");
    }
}
