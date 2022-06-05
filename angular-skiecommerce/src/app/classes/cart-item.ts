import { Product } from "./product";

export class CartItem {
    id!: number;
    name!: string;
    brand!: string;
    unitPrice!: number;
    imageUrl!: string;
    quantity : number;
    size!: number;

    constructor(product : Product){
        this.id=product.id;
        this.brand = product.brand;
        this.name = product.name;
        this.imageUrl = product.imageUrl;
        this.unitPrice = product.unitPrice;
        this.quantity = 1;
        this.size = product.size;
    }
}
