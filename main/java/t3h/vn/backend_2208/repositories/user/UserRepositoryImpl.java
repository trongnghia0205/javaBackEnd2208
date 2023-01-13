package t3h.vn.backend_2208.repositories.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import t3h.vn.backend_2208.dto.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
// Sử dụng 1 câu lệnh thuần sql phưc tạp
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<UserDto> findAllTest() {
        String sql = "SELECT * FROM user";
        List<UserDto> listUser2 = jdbcTemplate.query(sql, new RowMapper<UserDto>() {
            @Override
            public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserDto user = new UserDto();
                user.setId(rs.getLong("ID"));
                user.setFullName(rs.getString("FULL_NAME"));
                user.setAddress(rs.getString("ADDRESS"));
                return user;
            }
        });
        return listUser2;
    }
}
