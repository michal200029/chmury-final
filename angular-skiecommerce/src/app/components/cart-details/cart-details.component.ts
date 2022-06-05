import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/classes/cart-item';
import { CartService } from 'src/app/services/cart.service';
import { ProductListComponent } from '../product-list/product-list.component';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit{

  totalQuantity!: number;
  finalValue: number ;
  totalValue!: number;
  itemsInCart: CartItem[] = [];
  constructor(private cartService: CartService,
              private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.getCartData();
  }

  getCartData(){

    this.itemsInCart = this.cartService.productsInCart;
    this.cartService.totalValue.subscribe(
      data => { this.totalValue = data;})

    this.cartService.totalQuantity.subscribe(
      data => { this.totalQuantity = data;})
  }

  decrementQuantity(item: CartItem) {
    this.cartService.decrementQuantity(item);
  }

  incrementQuantity(item: CartItem){
    this.cartService.addToCart(item);
  }


}
