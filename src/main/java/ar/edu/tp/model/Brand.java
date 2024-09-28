package ar.edu.tp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Random;

@Entity
public class Brand {
    @Id
    private Long id;
    private String name;

    public Brand(String name) {
        this.name = name;
        this.id = new Random().nextLong();
    }

    public Brand() {

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
        Brand brand = (Brand) obj;
        return this.name.equals(brand.name) && this.id.equals(brand.id);
    }

    public String name() {
        return this.name;
    }
}
