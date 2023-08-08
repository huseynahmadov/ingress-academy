package az.ingress.payments.service;

import az.ingress.payments.dao.repository.PaymentRepository;
import az.ingress.payments.model.request.PaymentRequest;
import az.ingress.payments.model.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static az.ingress.payments.mapper.PaymentMapper.PAYMENT_MAPPER;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PaymentServiceHandler implements PaymentService {

    PaymentRepository paymentRepository;

    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(PAYMENT_MAPPER.mapToPaymentEntity(paymentRequest));
        return payment.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
        var payment = paymentRepository.findByOrderId(Long.valueOf(orderId));
        return PAYMENT_MAPPER.mapToPaymentResponse(payment);
    }

}
