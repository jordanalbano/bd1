package ar.edu.tp.model;

public class CreditCard {
    private String number;
    private String cvv;
    private String yearExpiration;
    private String monthExpiration;
    private CreditCardProvider creditCardProvider;

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

}
