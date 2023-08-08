package az.ingress.products.controller;

import az.ingress.products.model.request.ProductRequest;
import az.ingress.products.model.response.ProductResponse;
import az.ingress.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProductController {

    ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/reduce-quantity/{productId}")
    public void reduceQuantity(@PathVariable Long productId, @RequestParam Long quantity) {
        productService.reduceQuantity(productId, quantity);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public void createProduct(@RequestBody ProductRequest request) {
        productService.createProduct(request);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
