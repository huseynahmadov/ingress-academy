package az.ingress.orders.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CustomFeignException extends RuntimeException {

    Integer status;

    public CustomFeignException(String message, Integer status) {
        super(message);
        this.status = status;
    }

}
