package ar.edu.tp.model;

import java.util.List;

public class Client {
    private String name;
    private String lastname;
    private String dni;
    private String email;
    private String phone;
    private List<CreditCard> creditCards;
    public Client(String name, String lastname, String dni, String email, String phone, List<CreditCard> creditCards) {
        this.validate(name, lastname, dni, email, phone, creditCards);
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
        this.creditCards = creditCards;
    }

    private void validate(String name, String lastname, String dni, String email, String phone, List<CreditCard> creditCards) {
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
}
