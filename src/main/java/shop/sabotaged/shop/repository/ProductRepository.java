package shop.sabotaged.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.sabotaged.shop.enitity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
