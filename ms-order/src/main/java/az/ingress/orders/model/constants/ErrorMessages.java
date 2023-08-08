package az.ingress.orders.model.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {

    PRODUCT_NOT_FOUND("Product not found with ID : {}"),

    UNEXPECTED_ERROR("Something went wrong"),

    PRODUCT_SERVICE_UNAVAILABLE("Product service currently unavailable"),

    ERROR_CLIENT("Error occurred from client"),

    ORDER_NOT_FOUND("Order not found with id : {}");

    private final String message;

    public String getMessage(Long id) {
        return message.replace("{}", String.valueOf(id));
    }

}
