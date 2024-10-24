package ar.edu.tp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Getter;

@Entity
@Getter
public class NextNumber {

    @Id
    private long id;
    @Version
    private Long version;
    private int year;
    private int current;

    public NextNumber(int anio, int actual) {
        this.year = anio;
        this.current = actual;
    }

    protected NextNumber() {
        this.current = 0;
        this.year = 0;
    }

    public int recuperarSiguiente() {
        this.current += 1;
        return this.current;
    }


}