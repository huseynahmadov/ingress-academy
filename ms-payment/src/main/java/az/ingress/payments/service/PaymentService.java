package az.ingress.payments.service;

import az.ingress.payments.model.request.PaymentRequest;
import az.ingress.payments.model.response.PaymentResponse;

public interface PaymentService {

    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(String orderId);

}
