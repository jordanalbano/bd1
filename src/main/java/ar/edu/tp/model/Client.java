package ar.edu.tp.model;

import java.util.List;

public class Client {
    private String name;
    private String lastname;
    private String dni;
    private String email;
    private String phone;
    private List<CreditCard> creditCards;
    public Client(String name,
                  String lastname,
                  String dni, String email,
                  String phone,
                  List<CreditCard> creditCards) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
        this.creditCards = creditCards;
        this.validate();
    }

    public Client(String name, String lastname, String dni, String email) {
        this(name, lastname, dni, email, null, null);
    }


    private void validate() {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        if (lastname == null || lastname.isEmpty()) {
            throw new IllegalArgumentException("Lastname can't be null or empty");
        }
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("Dni can't be null or empty");
        }
        if (email == null || email.isEmpty() && !isValidEmail(email)) {
            throw new IllegalArgumentException("Email can't be null or empty");
        }
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Phone can't be null or empty");
        }
        if (creditCards == null) {
            throw new IllegalArgumentException("CreditCards can't be null");
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public void update(Client newClient) {
        newClient.validate();
        this.name = newClient.name;
        this.lastname = newClient.lastname;
        this.dni = newClient.dni;
        this.email = newClient.email;
        this.phone = newClient.phone;
        this.creditCards = newClient.creditCards;
    }

    public void addCard(CreditCard card) {
        this.creditCards.add(card);
    }

    public List<CreditCard> creditCards() {
        return this.creditCards;
    }

    public boolean cardBelongs(CreditCard card) {
        return creditCards().stream().anyMatch(c -> c.equals(card));
    }
}
