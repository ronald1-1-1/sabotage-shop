package shop.sabotaged.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.sabotaged.shop.enitity.VariantEntity;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity, Long> {
}
