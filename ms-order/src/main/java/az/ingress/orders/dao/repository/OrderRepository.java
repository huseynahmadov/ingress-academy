package az.ingress.orders.dao.repository;

import az.ingress.orders.dao.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
