package t3h.vn.backend_2208.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;
import t3h.vn.backend_2208.entities.ProductOrderEntity;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrderEntity, Long> {
    int countDistinctByUserIdAndStatus(Long userId, Integer status);

    List<ProductOrderEntity> findAllByUserIdAndStatus(Long userid, Integer status);
}
