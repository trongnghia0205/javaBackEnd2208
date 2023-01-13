package t3h.vn.backend_2208.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t3h.vn.backend_2208.entities.OrderEntity;
import t3h.vn.backend_2208.entities.ProductsEntity;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

}
