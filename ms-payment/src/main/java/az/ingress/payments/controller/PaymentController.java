package az.ingress.payments.controller;

import az.ingress.payments.model.request.PaymentRequest;
import az.ingress.payments.model.response.PaymentResponse;
import az.ingress.payments.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PaymentController {

    private PaymentService paymentService;

    @PostMapping
    public Long doPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.doPayment(paymentRequest);
    }

    @GetMapping("/order/{orderId}")
    public PaymentResponse getPaymentDetailsByOrderId(@PathVariable String orderId) {
        return paymentService.getPaymentDetailsByOrderId(orderId);
    }

}
