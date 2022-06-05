import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ShippingMethod } from '../classes/shipping-method';
import { PaymentMethod } from '../classes/payment-method';
import { Country } from '../classes/country';

@Injectable({
  providedIn: 'root'
})
export class CheckoutFormService {
  
  getAllShippingMethods(): Observable<ShippingMethod[]>{
    
    return Observable.create(obs => {
      obs.next("CARD")
      obs.next("PAYPAL")
      obs.next("TRANSFER")
    });
  }

  getAllPaymentMethods(): Observable<PaymentMethod[]>{ 
    return Observable.create(obs => {
      obs.next("INPOST")
      obs.next("DPD")
      obs.next("DHL")
    }); 
  }

  getAllCountries(): Observable<Country[]>{
    return Observable.create(obs => {
      obs.next("POLSKA")
      obs.next("NIEMCY")
      obs.next("FRANCJA")
    }); 
  }
}
