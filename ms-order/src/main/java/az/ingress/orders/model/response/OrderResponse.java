package az.ingress.orders.model.response;

import az.ingress.orders.model.dto.PaymentDetails;
import az.ingress.orders.model.dto.ProductDetails;
import az.ingress.orders.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
public class OrderResponse {

    Long id;
    Instant createdAt;
    OrderStatus status;
    BigDecimal amount;
    ProductDetails productDetails;
    PaymentDetails paymentDetails;

}
