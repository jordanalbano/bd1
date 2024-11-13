package ar.edu.tp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Brand implements Serializable {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;

    public Brand(String name) {
        this.name = name;
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
