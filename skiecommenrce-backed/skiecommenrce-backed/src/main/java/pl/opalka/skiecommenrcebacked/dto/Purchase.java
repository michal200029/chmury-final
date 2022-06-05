package pl.opalka.skiecommenrcebacked.dto;

import lombok.Getter;
import lombok.Setter;
import pl.opalka.skiecommenrcebacked.model.Address;
import pl.opalka.skiecommenrcebacked.model.Customer;
import pl.opalka.skiecommenrcebacked.model.OrderDetail;
import pl.opalka.skiecommenrcebacked.model.OrderItem;

import java.util.Set;

@Getter
@Setter
public class Purchase {
    private Customer customer;
    private Address address;
    private Set<OrderItem> orderItem;
    private OrderDetail orderInfo;
    private String paymentMethod;
    private String shippingMethod;
}