package shop.sabotaged.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.sabotaged.shop.enitity.VariantChangesEntity;

@Repository
public interface VariantChangesRepository extends JpaRepository<VariantChangesEntity, Long> {
}
