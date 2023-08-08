package az.ingress.products.dao.repository;

import az.ingress.products.dao.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
