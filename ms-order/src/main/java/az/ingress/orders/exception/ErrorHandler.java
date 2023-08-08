package az.ingress.orders.exception;

import az.ingress.orders.model.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static az.ingress.orders.model.constants.ErrorMessages.PRODUCT_SERVICE_UNAVAILABLE;
import static az.ingress.orders.model.constants.ErrorMessages.UNEXPECTED_ERROR;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@RestControllerAdvice
public class ErrorHandler {

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(INTERNAL_SERVER_ERROR)
//    public ErrorResponse handleInternalServerError(Exception exception) {
//        return new ErrorResponse(UNEXPECTED_ERROR.getMessage(), INTERNAL_SERVER_ERROR.value());
//    }

    @ExceptionHandler(FallbackException.class)
    @ResponseStatus(SERVICE_UNAVAILABLE)
    public ErrorResponse handleFeignResponse(FallbackException exception) {
        return new ErrorResponse(PRODUCT_SERVICE_UNAVAILABLE.getMessage(), SERVICE_UNAVAILABLE.value());
    }

}
