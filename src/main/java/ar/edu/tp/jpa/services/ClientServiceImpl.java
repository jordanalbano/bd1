package ar.edu.tp.jpa.services;

import ar.edu.tp.api.ClientService;
import ar.edu.tp.model.CreditCard;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final EntityManagerFactory emf;

    public ClientServiceImpl() {
        this.emf = Persistence.createEntityManagerFactory("jpa-objectdb");
    }

    @Override
    public void create(String name, String lastname, String dni, String email) {

    }

    @Override
    public void update(Long idCliente, String nombre) {

    }

    @Override
    public void addCard(Long idCliente, String nro, String marca) {

    }

    @Override
    public List<CreditCard> listCard(Long idCliente) {
        return List.of();
    }
}
