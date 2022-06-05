package pl.opalka.skiecommenrcebacked.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @Id
    private UUID id;

    private String orderTrackingNumber;

    private BigDecimal totalPrice;

    private OrderStatus status;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    private ShippingMethod shippingMethod;

    private PaymentMethod paymentMethod;

    public enum OrderStatus {
        PENDING
    }

    public enum ShippingMethod {
        INPOST, DPD, DHL
    }

    public enum PaymentMethod {
        CARD, PAYPAL, TRANSFER
    }

    public OrderDetail(
            UUID id,
            String orderTrackingNumber,
            BigDecimal totalPrice,
            OrderStatus status,
            ShippingMethod shippingMethod,
            PaymentMethod paymentMethod,
            Address address,
            Customer customer) {
        this.id = id;
        this.orderTrackingNumber = orderTrackingNumber;
        this.totalPrice = totalPrice;
        this.status = status;
        this.shippingMethod = shippingMethod;
        this.paymentMethod = paymentMethod;
        this.address = address;
        this.customer = customer;
    }
}

