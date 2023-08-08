package az.ingress.payments.dao.repository;

import az.ingress.payments.dao.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {

    PaymentEntity findByOrderId(Long orderId);

}
