package pl.opalka.skiecommenrcebacked.rest.model;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseApiDto {
   CustomerApiDto customer;
   AddressApiDto shippingAddress;
   OrderInfoApiDto orderInfo;
   List<OrderItemApiDto> orderItems;
   PaymentMethodApiDto paymentMethod;
   ShippingMethodApiDto shippingMethod;
}