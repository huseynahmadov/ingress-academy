package az.ingress.products.service;

import az.ingress.products.dao.entity.ProductEntity;
import az.ingress.products.dao.repository.ProductRepository;
import az.ingress.products.exception.InsufficientProductException;
import az.ingress.products.exception.ProductNotFoundException;
import az.ingress.products.model.request.ProductRequest;
import az.ingress.products.model.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static az.ingress.products.ProductMapper.PRODUCT_MAPPER;
import static az.ingress.products.model.constants.ErrorMessages.INSUFFICIENT_PRODUCT;
import static az.ingress.products.model.constants.ErrorMessages.PRODUCT_NOT_FOUND;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProductServiceHandler implements ProductService {

    ProductRepository productRepository;

    @Override
    public void createProduct(ProductRequest request) {
        productRepository.save(PRODUCT_MAPPER.mapToProductEntity(request));
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(PRODUCT_MAPPER::mapToProductResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        var product = fetchProduct(id);
        return PRODUCT_MAPPER.mapToProductResponse(product);
    }

    @Override
    public void deleteProduct(Long id) {
        var product = fetchProduct(id);
        productRepository.deleteById(product.getId());
    }

    @Override
    public void reduceQuantity(Long productId, Long quantity) {
        var product = fetchProduct(productId);

        if (product.getQuantity() < quantity) {
            throw new InsufficientProductException(INSUFFICIENT_PRODUCT.getMessage(quantity));
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }

    private ProductEntity fetchProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND.getMessage(id), BAD_REQUEST.value()));
    }

}
