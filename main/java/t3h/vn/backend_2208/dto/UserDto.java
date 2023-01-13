package t3h.vn.backend_2208.dto;

import lombok.Data;
import t3h.vn.backend_2208.validation.UniqueEmailValidation;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
public class UserDto {
    Long id;
//    @NotEmpty = ""
//    @NotNull = null;
    @NotBlank(message = "Email không được rỗng")// "", null, "         "
    @Email(message = "Không đúng định dạng email")
    @NotBlank(message = "Email is mandatory")
    @Column(unique=true)
    String userName;
    @NotBlank(message = "Mật khẩu không được rỗng")
    @Size(min = 6, message = "Nhập ít nhất 6 ký tự")
    String password;
    String rePassword;
    @NotBlank(message = "Họ và tên không được rỗng")
    String fullName;
    String address;
    @Min(value = 0, message = "Chỉ có 2 giá trị Kích hoạt hoặc tạm dừng")
    @Max(value = 1, message = "Chỉ có 2 giá trị Kích hoạt hoặc tạm dừng")
    Integer status;
    @NotBlank(message = "Quyền không được rỗng")
    String role;
}
