package ar.edu.tp.api;

import ar.edu.tp.model.CreditCard;

import java.util.Collection;

public interface CreditCardService {
    Collection<CreditCard> findAllByClientId(String id);
}
