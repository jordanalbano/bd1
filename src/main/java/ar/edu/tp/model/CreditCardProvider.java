package ar.edu.tp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class CreditCardProvider {
    @Id
    private String id;
    private String name;

    public CreditCardProvider(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public CreditCardProvider() {

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
    }
}
