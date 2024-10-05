package ar.edu.tp.web;

import ar.edu.tp.api.DiscountService;
import ar.edu.tp.model.Discount;
import ar.edu.tp.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/discounts")
public class DiscountController {
    private final DiscountService discountService;
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }
    @GetMapping("/currents")
    public ResponseEntity<Collection<Discount>> getCurrentDiscounts() {
        return ResponseEntity.ok(discountService.allCurrent());
    }
}
