package t3h.vn.backend_2208.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserChangePassWord {
    @Size(min = 6, message = "Nhập ít nhất 6 ký tự")
    String password;
    @Size(min = 6, message = "Nhập ít nhất 6 ký tự")
    String rePassword;

    @Size(min = 6, message = "Nhập ít nhất 6 ký tự")
    String oldPassword;
}
