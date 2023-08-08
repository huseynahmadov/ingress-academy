package az.ingress.orders.model.dto;

import az.ingress.orders.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDetails {

    Long id;
    PaymentType paymentType;
    Instant paymentDate;
    String paymentStatus;

}
