package pl.opalka.skiecommenrcebacked.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.opalka.skiecommenrcebacked.rest.model.PurchaseApiDto;
import pl.opalka.skiecommenrcebacked.rest.model.PurchaseResponse;
import pl.opalka.skiecommenrcebacked.service.PurchaseService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PurchaseController {
    
    private final PurchaseService purchaseService;

    @PostMapping("/api/purchase")
    public PurchaseResponse placeOrder(@RequestBody @Valid PurchaseApiDto purchaseApiDto) {
         return new PurchaseResponse(purchaseService.placeOrder(purchaseApiDto));
    }


}
