import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart-info',
  templateUrl: './cart-info.component.html',
  styleUrls: ['./cart-info.component.css']
})
export class CartInfoComponent implements OnInit {


  totalQuantity: number = 0;
  totalValue: number = 0;
  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.updateCartInfo();
  }

  updateCartInfo(){
    this.cartService.totalValue.subscribe(
      data => { this.totalValue = data });

      this.cartService.totalQuantity.subscribe(
        data => { this.totalQuantity = data });
  }

}
