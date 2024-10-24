package ar.edu.tp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class NextNumber {

    @Id
    private long id;

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

    public int anio() {
        return this.year;
    }

    public int actual() {
        return this.current;
    }

    private long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    private int getAnio() {
        return year;
    }

    private void setAnio(int anio) {
        this.year = anio;
    }

    private int getActual() {
        return current;
    }

    private void setActual(int current) {
        this.current = current;
    }

}