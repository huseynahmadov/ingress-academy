package az.ingress.orders.client;

import az.ingress.orders.client.decoder.CustomErrorDecoder;
import az.ingress.orders.exception.FallbackException;
import az.ingress.orders.model.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@FeignClient(
        name = "ms-payment",
        url = "${client.urls.ms-payment}",
        configuration = CustomErrorDecoder.class
)
public interface PaymentClient {

    @PostMapping("/api/v1/payments")
    Long doPayment(@RequestBody PaymentRequest paymentRequest);

    default Long fallback(Exception exception) {
        throw new FallbackException();
    }

}
