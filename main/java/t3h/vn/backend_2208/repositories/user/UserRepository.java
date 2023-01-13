package t3h.vn.backend_2208.repositories.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import t3h.vn.backend_2208.entities.UserEntity;
import t3h.vn.backend_2208.repositories.SearchingRepository;

import java.util.List;

public interface UserRepository extends SearchingRepository<UserEntity, Long>, UserRepositoryCustom {
    // jpql
    // ví dụ về hàm tìm kiếm phân trang
//    @Query("select u from UserEntity u where u.fullName like %:key% or u.userName like %:key%")
    @Query(value = "SELECT * FROM user  where FULL_NAME like %?1% or USER_NAME like %?1%", nativeQuery = true)
    Page<UserEntity> findAll(String key, Pageable pageable);

    // Tìm kiếm danh sách User có trạng thái active
    List<UserEntity> findAllByStatus(Integer status);

    UserEntity findFirstByUsername(String username);
}
