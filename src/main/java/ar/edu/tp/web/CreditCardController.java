package ar.edu.tp.web;

import ar.edu.tp.api.CreditCardService;
import ar.edu.tp.model.CreditCard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/credit-cards")
public class CreditCardController {
    private CreditCardService creditCardService;

    @GetMapping("/clients/{id}")
    public ResponseEntity<Collection<CreditCard>> findAllByClientId(@PathVariable String id) {
        return ResponseEntity.ok(creditCardService.findAllByClientId(id));
    }

}
