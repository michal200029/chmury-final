import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../classes/product';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { ProductCategory } from '../classes/product-category';
import { TokenStorageService } from './token-storage.service';
import { HttpHeaders } from '@angular/common/http';

let headers = new HttpHeaders();

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productUrl = 'http://localhost:8888/api/products';
  private categoryUrl = 'http://localhost:8888/api/product-category';
  

  constructor(private httpClient: HttpClient, private tokenStorage: TokenStorageService ) { }

  getProducts(categoryId: number,) : Observable<Product[]>{
    return this.httpClient.get(this.productUrl + "/" + categoryId).pipe(
      map(response => response['products']));
    
  }

  getAllCategories(): Observable<ProductCategory[]>{
    return this.httpClient.get(this.categoryUrl).pipe(
      map(response => response['productCategory']));
  }

}
