package pl.opalka.skiecommenrcebacked.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    private UUID id;

    private BigDecimal unitPrice;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id")
    private OrderDetail orderDetail;

    private Long productId;

    public OrderItem(UUID id, BigDecimal unitPrice, int quantity, Long productId) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.productId = productId;
    }
}