package tm.chmury.invoicegenerator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderDetail {

    @Id
    private UUID id;

    private String orderTrackingNumber;

    private BigDecimal totalPrice;

    private UUID invoiceId;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public OrderDetail(
            UUID id,
            String orderTrackingNumber,
            BigDecimal totalPrice,
            Address address,
            Customer customer) {
        this.id = id;
        this.orderTrackingNumber = orderTrackingNumber;
        this.totalPrice = totalPrice;
        this.address = address;
        this.customer = customer;
    }
}
