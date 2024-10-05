package ar.edu.tp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class CreditCard {
    @Id
    private String id;
    private String number;
    private String cvv;
    private String yearExpiration;
    private String monthExpiration;
    @ManyToOne
    private CreditCardProvider creditCardProvider;
    @ManyToOne
    private Client client;

    public CreditCard(String number,
                      String cvv,
                      String yearExpiration,
                      String monthExpiration,
                      CreditCardProvider creditCardProvider) {
        this.number = number;
        this.cvv = cvv;
        this.yearExpiration = yearExpiration;
        this.monthExpiration = monthExpiration;
        this.creditCardProvider = creditCardProvider;
    }

    public CreditCard() {

    }

    public void client(Client client){
        this.client = client;
    }
}
