package ar.edu.tp.model;

import java.time.LocalDate;

public abstract class Discount {
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected int discountPercentage;


    public Discount(LocalDate startDate, LocalDate endDate, int discountPercentage) {
        this.validateDates(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercentage = discountPercentage;
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
