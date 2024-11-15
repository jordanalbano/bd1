package ar.edu.tp.web;

import ar.edu.tp.api.SaleService;
import ar.edu.tp.dto.BrandDTO;
import ar.edu.tp.model.Sale;
import ar.edu.tp.model.SaleDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
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

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> generateSale(@RequestBody String sale) throws JsonProcessingException {
        var jsonMapper = new JsonMapper();
        jsonMapper.findAndRegisterModules();
        var saleDto = jsonMapper.readValue(sale, SaleDto.class);
        service.create(saleDto);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/last-three/{clientId}")
    public ResponseEntity<String> getLastThreeSales(@PathVariable String clientId) throws JsonProcessingException {
        var jsonMapper = new JsonMapper();
        jsonMapper.findAndRegisterModules();
        return ResponseEntity.ok().body(jsonMapper.writeValueAsString(service.getLastThreeSalesByClientId(clientId)));
    }

}
