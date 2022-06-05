import { Directive, ElementRef, HostListener, Input, OnInit, Renderer2 } from '@angular/core';
import { Product } from '../classes/product';


@Directive({
  selector: '[appDetails]'
})
export class DetailsDirective {

  private window; //<p>
  @Input()
  public product: Product;
  @Input()
  public categoryId: number;
  constructor(private el: ElementRef, private renderer: Renderer2) { 
    this.window = this.renderer.createElement('p');
  }
  
  
  @HostListener(`mouseenter`)
  showDetials(){
    
    if(this.categoryId==1){
    this.window.innerHTML = 
      "Radius : " + this.product.special + "<br/>" +
      "Units in stock : " + this.product.unitsInStock;
    }else if(this.categoryId==2){
      this.window.innerHTML = 
      "Flex : " + this.product.special + "<br/>" +
      "Units in stock : " + this.product.unitsInStock;
    }else     {
      this.window.innerHTML = 
      "Units in stock : " + this.product.unitsInStock;
    }
    

   // this.el.nativeElement.innerHTML = "";
    this.renderer.setStyle(this.window, 'background-color','#FFDFDD' );
    this.renderer.setStyle(this.window, 'font-size','25px' );
    this.renderer.appendChild(this.el.nativeElement, this.window); 
    
  }

  @HostListener('mouseleave')
  hideDetials(){
    this.renderer.removeChild(this.el.nativeElement, this.window);
  }
}
