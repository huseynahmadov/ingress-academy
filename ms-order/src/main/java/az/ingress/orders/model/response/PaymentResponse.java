package az.ingress.orders.model.response;

import az.ingress.orders.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    private Long id;
    private String status;
    private PaymentType paymentType;
    private BigDecimal amount;
    private Instant createdAt;
    private Long orderId;

}