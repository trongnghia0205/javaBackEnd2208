package t3h.vn.backend_2208.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SearchingRepository<T, ID> extends JpaRepository<T, ID> {

    Page<T> findAll(String key, Pageable pageable);
}
