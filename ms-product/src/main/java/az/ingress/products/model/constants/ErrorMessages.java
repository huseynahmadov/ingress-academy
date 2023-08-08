package az.ingress.products.model.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {

    UNEXPECTED_ERROR("Something went wrong"),
    PRODUCT_NOT_FOUND("Product not found with ID : {}"),
    INSUFFICIENT_PRODUCT("Insufficient product : {}");

    private final String message;

    public String getMessage(Long id) {
        return message.replace("{}", String.valueOf(id));
    }

}
