import { Address } from "./address";
import { Customer } from "./customer";
import { OrderInfo } from "./order-info";
import { OrderItem } from "./order-item";
import { PaymentMethod } from "./payment-method";
import { ShippingMethod } from "./shipping-method";

export class Purchase {
    customer!: Customer;
    shippingAddress!: Address;
    orderInfo!: OrderInfo;
    orderItems!: OrderItem[];
    paymentMethod!: PaymentMethod;
    shippingMethod!: ShippingMethod;
}
