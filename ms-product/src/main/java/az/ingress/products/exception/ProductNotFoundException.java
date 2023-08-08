package az.ingress.products.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ProductNotFoundException extends RuntimeException {

    private Integer status;

    public ProductNotFoundException(String message, Integer status) {
        super(message);
        this.status = status;
    }

}
