package pl.opalka.skiecommenrcebacked.rest.model;

import lombok.Data;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class OrderItemApiDto {
    Long id;
    @Positive
    int quantity;
    @Positive
    BigDecimal unitPrice;
}
