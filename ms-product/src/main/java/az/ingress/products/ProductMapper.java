package az.ingress.products;

import az.ingress.products.dao.entity.ProductEntity;
import az.ingress.products.model.request.ProductRequest;
import az.ingress.products.model.response.ProductResponse;

public enum ProductMapper {

    PRODUCT_MAPPER;

    public ProductEntity mapToProductEntity(ProductRequest productRequest) {
        return ProductEntity.builder()
                .name(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();
    }

    public ProductResponse mapToProductResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .build();
    }

}
