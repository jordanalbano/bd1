package model;

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
            throw new IllegalArgumentException("The start date must be before the end date");
        }
    }

    public  long discountPercentage(){
        return this.discountPercentage;
    }
}
