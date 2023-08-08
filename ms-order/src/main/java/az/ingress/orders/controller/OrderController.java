package az.ingress.orders.controller;

import az.ingress.orders.model.request.OrderRequest;
import az.ingress.orders.model.response.OrderResponse;
import az.ingress.orders.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OrderController {

    OrderService orderService;

    @PostMapping
    public Long placeOrder(@RequestBody OrderRequest request) {
        Long orderId = orderService.placeOrder(request);
        return orderId;
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderDetails(@PathVariable Long id) {
        return orderService.getOrderDetails(id);
    }

}
