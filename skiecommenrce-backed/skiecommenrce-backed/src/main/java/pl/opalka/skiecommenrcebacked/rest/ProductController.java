package pl.opalka.skiecommenrcebacked.rest;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.opalka.skiecommenrcebacked.repository.ProductCategoryRepository;
import pl.opalka.skiecommenrcebacked.repository.ProductRepository;
import pl.opalka.skiecommenrcebacked.model.Product;
import pl.opalka.skiecommenrcebacked.model.ProductCategory;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin("http://localhost:4200")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @GetMapping("/api/products/{categoryId}")
    public ProductApi getAllProducts(@PathVariable long categoryId) {
        return new ProductApi(productRepository.findAllAvailableByCategoryId(categoryId));
    }

    @GetMapping("/api/product-category")
    public ProductCategoryApi getAllProductCategories() {
        return new ProductCategoryApi(productCategoryRepository.findAll());
    }

    @Value
    static class ProductCategoryApi {
        List<ProductCategory> productCategory;
    }

    @Value
    static class ProductApi {
        List<Product> products;
    }
}
