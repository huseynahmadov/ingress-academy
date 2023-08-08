package az.ingress.orders.client;

import az.ingress.orders.client.decoder.CustomErrorDecoder;
import az.ingress.orders.exception.FallbackException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@CircuitBreaker(name = "reduce-quantity", fallbackMethod = "fallback")
@FeignClient(name = "ms-product",
        url = "${client.urls.ms-product}/api/v1/products",
        configuration = CustomErrorDecoder.class)
public interface ProductClient {

    @PutMapping("/reduce-quantity/{productId}")
    void reduceQuantity(@PathVariable Long productId, @RequestParam Long quantity);

    default void fallback(Exception e) {
        throw new FallbackException();
    }

}

