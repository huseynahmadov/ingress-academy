package az.ingress.orders.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class OrderNotFoundException extends RuntimeException {

    Integer status;

    public OrderNotFoundException(String message, Integer status) {
        super(message);
        this.status = status;
    }

}
