package pl.opalka.skiecommenrcebacked.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.opalka.skiecommenrcebacked.model.*;
import pl.opalka.skiecommenrcebacked.repository.*;
import pl.opalka.skiecommenrcebacked.rest.expception.QuantityException;
import pl.opalka.skiecommenrcebacked.rest.model.PurchaseApiDto;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class PurchaseService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

    @Transactional
    public String placeOrder(PurchaseApiDto purchaseApiDto) {

        for(var oi : purchaseApiDto.getOrderItems()) {
            Product product = productRepository.findById(oi.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
            if (product.getUnitsInStock() < oi.getQuantity()) {
                throw new QuantityException(String.format(
                        "Brak %s sztuk dla przedmiotu o nazwie %s. Dostepne %s ",
                        oi.getQuantity(),
                        product.getName(),
                        product.getUnitsInStock())
                );
            } else {
                product.setUnitsInStock(product.getUnitsInStock() - oi.getQuantity());
            }
        }

        Address address = new Address(
                UUID.randomUUID(),
                purchaseApiDto.getShippingAddress().getCity(),
                purchaseApiDto.getShippingAddress().getStreet(),
                purchaseApiDto.getShippingAddress().getCountry().getName(),
                purchaseApiDto.getShippingAddress().getStreet()
        );
        addressRepository.save(address);

        Customer customer = new Customer(
                UUID.randomUUID(),
                purchaseApiDto.getCustomer().getFirstName(),
                purchaseApiDto.getCustomer().getFirstName(),
                purchaseApiDto.getCustomer().getEmail(),
                purchaseApiDto.getCustomer().getPhoneNumber()
        );
        customerRepository.save(customer);

        var orderTrackingNumber = generateOrderTrackingNumber();
        OrderDetail orderDetail = new OrderDetail(
                UUID.randomUUID(),
                orderTrackingNumber,
                purchaseApiDto.getOrderInfo().getTotalPrice(),
                OrderDetail.OrderStatus.PENDING,
                valueFromString(OrderDetail.ShippingMethod.values(), purchaseApiDto.getShippingMethod().getName()),
                valueFromString(OrderDetail.PaymentMethod.values(), purchaseApiDto.getPaymentMethod().getName()),
                address,
                customer
        );

        List<OrderItem> orderItems = new ArrayList<>();

        for(var oi : purchaseApiDto.getOrderItems()) {
            orderItems.add(new OrderItem(
                    UUID.randomUUID(),
                    oi.getUnitPrice(),
                    oi.getQuantity(),
                    oi.getId()
            ));
        }

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderDetail(orderDetail);
        }

        orderDetailRepository.save(orderDetail);
        orderItemRepository.saveAll(orderItems);


        return orderTrackingNumber;
    }


    private static String generateOrderTrackingNumber() {
        Random random = new Random();
        String letter = "ABCDEFGHIJKLMNOPERSTUWXYZ1234567890";

        StringBuilder builder = new StringBuilder();

        for (int i=0; i<16; i++)
            builder.append(letter.charAt(random.nextInt(letter.length())));

        return builder.toString();
    }

    private static <T extends Enum<T>> T valueFromString(T[] values, String str) {
        return Arrays.stream(values).filter(v -> v.name().equals(str)).findFirst().orElse(null);
    }
}
