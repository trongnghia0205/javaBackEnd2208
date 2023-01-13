package t3h.vn.backend_2208.repositories.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import t3h.vn.backend_2208.entities.ProductsEntity;
import t3h.vn.backend_2208.entities.UserEntity;
import t3h.vn.backend_2208.repositories.SearchingRepository;

import java.util.List;

public interface ProductRepository extends SearchingRepository<ProductsEntity, Long> {
    @Query(value = "SELECT * FROM products  where full_description like %?1% or " +
            "short_description like %?1% or alias like %?1% ", nativeQuery = true)
    Page<ProductsEntity> findAll(String key, Pageable pageable);

    @Query(value = "select * from products order by updated_time desc limit 4", nativeQuery = true)
    List<ProductsEntity> findTop4Product();
}
