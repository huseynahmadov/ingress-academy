package az.ingress.orders.mapper;

import az.ingress.orders.dao.OrderEntity;
import az.ingress.orders.model.dto.PaymentDetails;
import az.ingress.orders.model.dto.ProductDetails;
import az.ingress.orders.model.request.OrderRequest;
import az.ingress.orders.model.request.PaymentRequest;
import az.ingress.orders.model.response.OrderResponse;
import az.ingress.orders.model.response.PaymentResponse;
import az.ingress.orders.model.response.ProductResponse;

import java.time.Instant;
import java.util.UUID;

import static az.ingress.orders.model.enums.OrderStatus.CREATED;

public enum OrderMapper {

    ORDER_MAPPER;

    public OrderEntity mapToOrderEntity(OrderRequest orderRequest) {
        return OrderEntity.builder()
                .amount(orderRequest.getTotalAmount())
                .status(CREATED)
                .productId(orderRequest.getProductId())
                .createdAt(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
    }

    public PaymentRequest mapToPaymentRequest(OrderRequest orderRequest, OrderEntity orderEntity) {
        return PaymentRequest.builder()
                .orderId(orderEntity.getId())
                .paymentType(orderRequest.getPaymentType())
                .amount(orderRequest.getTotalAmount())
                .referenceNumber(UUID.randomUUID().toString())
                .build();
    }

    public ProductDetails mapToProductDetails(ProductResponse productResponse) {
        return ProductDetails
                .builder()
                .productName(productResponse.getName())
                .productId(productResponse.getId())
                .quantity(productResponse.getQuantity())
                .price(productResponse.getPrice())
                .build();
    }

    public PaymentDetails mapToPaymentDetails(PaymentResponse paymentResponse) {
        return PaymentDetails
                .builder()
                .id(paymentResponse.getId())
                .paymentStatus(paymentResponse.getStatus())
                .paymentDate(paymentResponse.getCreatedAt())
                .paymentType(paymentResponse.getPaymentType())
                .build();
    }

    public OrderResponse mapToOrderResponse(OrderEntity orderEntity,
                                            ProductDetails productDetails,
                                            PaymentDetails paymentDetails) {
        return OrderResponse.builder()
                .id(orderEntity.getId())
                .status(orderEntity.getStatus())
                .amount(orderEntity.getAmount())
                .createdAt(orderEntity.getCreatedAt())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();
    }

}
