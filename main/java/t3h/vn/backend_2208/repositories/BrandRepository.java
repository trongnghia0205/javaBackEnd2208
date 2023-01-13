package t3h.vn.backend_2208.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import t3h.vn.backend_2208.entities.BrandEntity;

public interface BrandRepository extends SearchingRepository<BrandEntity, Long> {
    @Query(value = "SELECT * FROM brand  where NAME like %?1% or DESCRIPTION like %?1%", nativeQuery = true)
    Page<BrandEntity> findAll(String key, Pageable pageable);
}
