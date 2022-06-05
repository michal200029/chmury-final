import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'
import { Country } from 'src/app/classes/country';
import { OrderInfo } from 'src/app/classes/order-info';
import { OrderItem } from 'src/app/classes/order-item';
import { PaymentMethod } from 'src/app/classes/payment-method';
import { Purchase } from 'src/app/classes/purchase';
import { ShippingMethod } from 'src/app/classes/shipping-method';
import { CartService } from 'src/app/services/cart.service';
import { CheckoutFormService } from 'src/app/services/checkout-form.service';
import { CheckoutService } from 'src/app/services/checkout.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  shippingMethods: ShippingMethod[] = [
    new ShippingMethod(1, "INPOST"),
    new ShippingMethod(2, "DPD"),
    new ShippingMethod(3, "DHL")
  ];
  paymentMethods: PaymentMethod[] = [
    new PaymentMethod(1, "CARD"),
    new PaymentMethod(2, "PAYPAL"),
    new PaymentMethod(3, "TRANSFER")
  ];
  countries: Country[] = [      
      new Country(1, "POLSKA"),
      new Country(2, "NIEMCY"),
      new Country(3, "FRANCJA")
    ]

  totalValue: number;
  totalQuantity: number;

  checkoutFormGroup!: FormGroup;
  constructor(private formBuilder: FormBuilder,
              private cartService: CartService,
              private checkoutFromService: CheckoutFormService,
              private checkoutService: CheckoutService,
              private router: Router) { }

  
  ngOnInit(): void {
    this.checkoutFormGroup = this.formBuilder.group({
      customer: this.formBuilder.group({
        firstName: new FormControl('',[Validators.required,Validators.minLength(2)]),
        lastName: new FormControl('',[Validators.required,Validators.minLength(2)]),
        email: new FormControl('',[Validators.required,Validators.email]),
        phoneNumber: new FormControl('',[Validators.required,Validators.minLength(9),Validators.maxLength(9), Validators.pattern("^[0-9]*$")])
      }),
      shippingAddress: this.formBuilder.group({
        country : new FormControl('',[Validators.required]),
        city : new FormControl('',[Validators.required,Validators.minLength(5)]),
        street : new FormControl('',[Validators.required,Validators.minLength(5)]),
        zipCode : new FormControl('',[Validators.required,Validators.minLength(6)]),
      }),
      paymentMethod: this.formBuilder.group({
        id: new FormControl('',[Validators.required])
      }),
      shippingMethod: this.formBuilder.group({
        id: new FormControl('',[Validators.required])
      })
    })
    
    this.cartService.totalQuantity.subscribe(
      data => { this.totalQuantity = data});
      
    this.cartService.totalValue.subscribe(
      data => { this.totalValue = data});
    
  };
  onSubmit(){
    if (this.checkoutFormGroup.invalid) {
      this.checkoutFormGroup.markAllAsTouched();
     return ; // zakomentowac w przypadku testow. Pozwoli to pobierac dane z chroma z zapisanych danych
    }

    let orderInfo = new OrderInfo;
      orderInfo.totalPrice = this.totalValue;
      orderInfo.totalQuantity = this.totalQuantity;

    const cartItems = this.cartService.productsInCart;

    let orderItems: OrderItem[] = cartItems.map(item => new OrderItem(item));
    let purchase = new Purchase;

    purchase.customer = this.checkoutFormGroup.controls['customer'].value;

    purchase.shippingAddress = this.checkoutFormGroup.controls['shippingAddress'].value;
    purchase.shippingMethod = this.checkoutFormGroup.controls['shippingMethod'].value.id;
    purchase.paymentMethod = this.checkoutFormGroup.controls['paymentMethod'].value.id;

    purchase.orderInfo = orderInfo;
    purchase.orderItems = orderItems;

    this.checkoutService.placeOrder(purchase).subscribe({
      next: response => {
        alert(`Your order has been received.\nOrder tracking number: ${response.orderTrackingNumber}`);
        this.resetCart();
        // this.router.navigateByUrl("/products");
      },
      error: err => {
        console.log(err)
        if (err.status == 403) {
          alert(`Musisz być zalogowany lub twoj token wygasl`);
        } else if (err.status == 409) {
          alert(`${err.error}`);
        } else {
          alert(`Wystapil blad: ${err.message}`);
        }
      }
    });
  }

  resetCart(){
    this.cartService.productsInCart = [];
    this.cartService.totalValue.next(0);
    this.cartService.totalQuantity.next(0);
    
    this.checkoutFormGroup.reset();

    this.router.navigateByUrl("/products");
  }

  get firstName(){  return this.checkoutFormGroup.get('customer.firstName') }
  get lastName(){  return this.checkoutFormGroup.get('customer.lastName') }
  get email(){  return this.checkoutFormGroup.get('customer.email') }
  get phoneNumber(){  return this.checkoutFormGroup.get('customer.phoneNumber') }

  get shippingAddressStreet(){  return this.checkoutFormGroup.get('shippingAddress.street') }
  get shippingAddressCity(){  return this.checkoutFormGroup.get('shippingAddress.city') }
  get shippingAddressCountry(){  return this.checkoutFormGroup.get('shippingAddress.country') }
  get shippingAddressZipCode(){  return this.checkoutFormGroup.get('shippingAddress.zipCode') }

  get paymentMethodType(){  return this.checkoutFormGroup.get('paymentMethod.type') }
  get shippingMethodType(){  return this.checkoutFormGroup.get('shippingMethod.type') }
}
