import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CartItem } from 'src/app/classes/cart-item';
import { Product } from 'src/app/classes/product';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];
  currentCategoryId: number = 1 ; 
  previousCategoryId: number = 1;

  currentPage: number=0;
  pageSize: number=10;
  totalElements: number=0;

  mouseEnetered!: boolean;
  constructor(private productService: ProductService,
              private cartService: CartService,
              private activeRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(() => {
      this.handleListProduct();
    });
  }

  handleListProduct(){
    const hasCategoryId: boolean = this.activeRoute.snapshot.paramMap.has('id');

    if(hasCategoryId){
      this.currentCategoryId = Number(this.activeRoute.snapshot.paramMap.get('id'));
    }else 
      this.currentCategoryId = 1

    if (this.previousCategoryId != this.currentCategoryId)
      this.currentPage = 0;

     this.previousCategoryId = this.currentCategoryId;

  this.productService.getProducts(this.currentCategoryId)
                    .subscribe(data => {this.products = data;});
  
  }

  addToCart(product: Product){
      const newItem = new CartItem(product)
      this.cartService.addToCart(newItem);
  }

  updatePage($event){
    this.pageSize = $event.target.value;
    this.currentPage = 0;
    this.handleListProduct();
    }
  
}
