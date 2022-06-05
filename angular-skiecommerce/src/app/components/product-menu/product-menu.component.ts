import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductCategory } from 'src/app/classes/product-category';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-menu',
  templateUrl: './product-menu.component.html',
  styleUrls: ['./product-menu.component.css']
})
export class ProductMenuComponent implements OnInit {

  productCategories: ProductCategory[] = [];
  constructor(private productService: ProductService,
              private route: Router,
              private activedRoute: ActivatedRoute) { }

  ngOnInit(): void {
        this.handleListCategories();
  }


  handleListCategories(){
    this.productService.getAllCategories()
      .subscribe(data => {
        this.productCategories = data;
      });
      console.log(this.productCategories)
  }

}
