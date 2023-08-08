package az.ingress.payments.model.request;

import az.ingress.payments.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class PaymentRequest {

    Long orderId;
    BigDecimal amount;
    String referenceNumber;
    PaymentType paymentType;

}
