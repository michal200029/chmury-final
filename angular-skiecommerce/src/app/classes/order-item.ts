import { ThrowStmt } from "@angular/compiler";
import { CartItem } from "./cart-item";

export class OrderItem {
    id: number;
    image_url: string;
    quantity: number;
    unitPrice: number;

    constructor(item: CartItem){
        this.id = item.id;
        this.image_url = item.imageUrl;
        this.quantity = item.quantity;
        this.unitPrice = item.unitPrice
    }
}
