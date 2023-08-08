package az.ingress.payments.mapper;

import az.ingress.payments.dao.entity.PaymentEntity;
import az.ingress.payments.model.request.PaymentRequest;
import az.ingress.payments.model.response.PaymentResponse;

import java.time.Instant;

import static az.ingress.payments.model.enums.PaymentStatus.SUCCESS;

public enum PaymentMapper {

    PAYMENT_MAPPER;

    public PaymentEntity mapToPaymentEntity(PaymentRequest paymentRequest) {
        return PaymentEntity.builder()
                .createdAt(Instant.now())
                .paymentType(paymentRequest.getPaymentType())
                .status(SUCCESS)
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();
    }

    public PaymentResponse mapToPaymentResponse(PaymentEntity paymentEntity) {
        return PaymentResponse.builder()
                .id(paymentEntity.getId())
                .paymentType(paymentEntity.getPaymentType())
                .createdAt(paymentEntity.getCreatedAt())
                .orderId(paymentEntity.getOrderId())
                .status(paymentEntity.getStatus())
                .amount(paymentEntity.getAmount())
                .build();
    }
}
