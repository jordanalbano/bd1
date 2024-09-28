package ar.edu.tp.model;

public class CreditCardProvider {
    private String name;

    public CreditCardProvider(String name) {
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        CreditCardProvider creditCardProvider = (CreditCardProvider) obj;
        return this.name.equals(creditCardProvider.name);
    }}
