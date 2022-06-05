import { ProductCategory } from "./product-category";

export class Product {

    id!: number;
    brand!: string;
    name!: string;
    size!: number;
    condition!: string;
    unitPrice!: number;
    unitsInStock!: number; 
    special!: string;
    imageUrl!: string;
    productKey!: string;
    cateogry!: ProductCategory
}
