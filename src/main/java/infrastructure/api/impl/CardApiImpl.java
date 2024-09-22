package infrastructure.api.impl;

import dto.CardDTO;
import infrastructure.api.CardApi;

public class CardApiImpl implements CardApi {
    public boolean CardIsActive(CardDTO cardNumber) {
        return true;
    }
}
