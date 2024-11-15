package ar.edu.tp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
@Entity
@Getter
@Setter
public class Discount {
    @Id
    @UuidGenerator
    protected String id;
    @JsonFormat(pattern = "yyyy-MM-dd")

    protected LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")

    protected LocalDate endDate;
    protected int discountPercentage;


    public Discount(LocalDate startDate, LocalDate endDate, int discountPercentage) {
        this.validateDates(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercentage = discountPercentage;
    }

    public Discount() {

    }

    private void validateDates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("la fecha de inicio no puede ser posterior a la fecha de fin");
        }
    }

    public  long discountPercentage(){
        return this.discountPercentage;
    }
}
