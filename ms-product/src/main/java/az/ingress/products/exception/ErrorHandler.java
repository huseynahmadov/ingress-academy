package az.ingress.products.exception;

import az.ingress.products.model.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static az.ingress.products.model.constants.ErrorMessages.UNEXPECTED_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalServerError(Exception exception) {
        return new ErrorResponse(UNEXPECTED_ERROR.getMessage(), INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), exception.getStatus());
    }

}
