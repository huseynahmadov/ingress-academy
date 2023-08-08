package az.ingress.orders.client.decoder;

import az.ingress.orders.exception.CustomFeignException;
import az.ingress.orders.model.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;

import static az.ingress.orders.model.constants.ErrorMessages.ERROR_CLIENT;

public class CustomErrorDecoder implements ErrorDecoder {

    @SneakyThrows
    @Override
    public Exception decode(String methodKey, Response response) {
        var objectMapper = new ObjectMapper();
        var errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);

        return new CustomFeignException(ERROR_CLIENT.getMessage(), errorResponse.getStatus());
    }

}
