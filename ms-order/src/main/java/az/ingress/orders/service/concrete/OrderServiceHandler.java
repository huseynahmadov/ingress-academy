package az.ingress.orders.service.concrete;

import az.ingress.orders.client.PaymentClient;
import az.ingress.orders.client.ProductClient;
import az.ingress.orders.dao.repository.OrderRepository;
import az.ingress.orders.model.constants.ErrorMessages;
import az.ingress.orders.model.enums.OrderStatus;
import az.ingress.orders.model.request.OrderRequest;
import az.ingress.orders.model.response.OrderResponse;
import az.ingress.orders.model.response.PaymentResponse;
import az.ingress.orders.model.response.ProductResponse;
import az.ingress.orders.service.abstraction.OrderService;
import az.ingress.orders.service.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static az.ingress.orders.mapper.OrderMapper.ORDER_MAPPER;
import static az.ingress.orders.model.enums.OrderStatus.PAYMENT_FAILED;
import static az.ingress.orders.model.enums.OrderStatus.PLACED;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OrderServiceHandler implements OrderService {

    OrderRepository orderRepository;
    PaymentClient paymentClient;
    ProductClient productClient;
    RestTemplate restTemplate;

    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        productClient.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        var orderEntity = orderRepository.save(ORDER_MAPPER.mapToOrderEntity(orderRequest));
        var paymentRequest = ORDER_MAPPER.mapToPaymentRequest(orderRequest, orderEntity);
        OrderStatus orderStatus = null;

        try {
            paymentClient.doPayment(paymentRequest);
            orderStatus = PLACED;
        } catch (Exception e) {
            orderStatus = PAYMENT_FAILED;
        }

        orderEntity.setStatus(orderStatus);
        orderRepository.save(orderEntity);

        return orderEntity.getId();
    }

    @Override
    public OrderResponse getOrderDetails(Long id) {
        var orderEntity = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException(ErrorMessages.ORDER_NOT_FOUND.getMessage(id), BAD_REQUEST.value()));

        var productResponse = restTemplate.getForObject(
                "http://localhost:8080/api/v1/products/" + orderEntity.getProductId(),
                ProductResponse.class);

        var paymentResponse = restTemplate.getForObject("http://localhost:8082/api/v1/payments/order/" + orderEntity.getId(),
                PaymentResponse.class);

        var productDetails = ORDER_MAPPER.mapToProductDetails(Objects.requireNonNull(productResponse));
        var paymentDetails = ORDER_MAPPER.mapToPaymentDetails(Objects.requireNonNull(paymentResponse));
        var orderResponse = ORDER_MAPPER.mapToOrderResponse(orderEntity, productDetails, paymentDetails);

        return orderResponse;
    }

}
