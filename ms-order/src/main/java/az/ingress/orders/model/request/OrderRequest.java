package az.ingress.orders.model.request;

import az.ingress.orders.model.enums.PaymentType;
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
public class OrderRequest {

    Long productId;
    BigDecimal totalAmount;
    Long quantity;
    PaymentType paymentType;

}
