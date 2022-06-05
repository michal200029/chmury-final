import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { CartItem } from '../classes/cart-item';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  totalQuantity:  Subject<number> = new BehaviorSubject<number>(0);
  totalValue:  Subject<number> = new BehaviorSubject<number>(0);
  productsInCart: CartItem[] = [];
  constructor() { 

  }

  addToCart(item: CartItem){
    let alreadyExistsInCart: boolean = false;
    let existingCartItem!: CartItem;
    

    this.productsInCart.forEach(element => {
        if(item.id == element.id){
          alreadyExistsInCart = true;
          existingCartItem = element;
        }    
    });
    if(alreadyExistsInCart){
      existingCartItem.quantity++;
    }else
      this.productsInCart.push(item);
   
    this.compute()

  }

  decrementQuantity(item: CartItem){
    item.quantity--;
    if(item.quantity<=0){
      this.remove(item);
    }else
      this.compute()
  }

  remove(item){
    const itemToDeleteID = this.productsInCart.findIndex(searching => searching.id === item.id);
    if(itemToDeleteID > -1){
      this.productsInCart.splice(itemToDeleteID,1);
      this.compute();
    }

  }

  compute(){
    let LocalValue: number = 0;
    let LocalQuantity: number = 0;
    this.productsInCart.forEach(element => {
      LocalValue += element.quantity * element.unitPrice;
      LocalQuantity += element.quantity;
    });

    this.totalValue.next(LocalValue);
    this.totalQuantity.next(LocalQuantity);
  }

}
