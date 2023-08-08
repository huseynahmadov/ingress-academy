package az.ingress.orders.service.abstraction;

import az.ingress.orders.model.request.OrderRequest;
import az.ingress.orders.model.response.OrderResponse;

public interface OrderService {

    Long placeOrder(OrderRequest request);

    OrderResponse getOrderDetails(Long orderId);

}
