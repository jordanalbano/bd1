package ar.edu.tp.jpa;

import ar.edu.tp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClientRepository extends JpaRepository<Client, Long> {
}
