package ar.edu.tp.web;

import ar.edu.tp.api.SaleService;
import ar.edu.tp.dto.BrandDTO;
import ar.edu.tp.model.Sale;
import ar.edu.tp.model.SaleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {
    private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> generateSale(@RequestBody Sale sale) {
        service.create(sale);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/last-three/{clientId}")
    public ResponseEntity<List<SaleDto>> getLastThreeSales(@PathVariable String clientId) {
        return ResponseEntity.ok().body(service.getLastThreeSalesByClientId(clientId));
    }

}
