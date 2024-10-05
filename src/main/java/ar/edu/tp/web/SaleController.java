package ar.edu.tp.web;

import ar.edu.tp.api.SaleService;
import ar.edu.tp.model.Sale;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SaleController {
    private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Void> generateSale(@RequestBody Sale sale) {
        service.create(sale);
        return ResponseEntity.noContent().build();
    }

}
