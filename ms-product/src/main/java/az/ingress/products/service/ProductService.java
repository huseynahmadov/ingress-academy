package az.ingress.products.service;

import az.ingress.products.model.request.ProductRequest;
import az.ingress.products.model.response.ProductResponse;

import java.util.List;

public interface ProductService {

    void createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    void deleteProduct(Long id);

    void reduceQuantity(Long productId, Long quantity);

}
