package t3h.vn.backend_2208.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import t3h.vn.backend_2208.dto.ResponseDto;
import t3h.vn.backend_2208.dto.ResponseTableDto;
import t3h.vn.backend_2208.dto.UserChangePassWord;
import t3h.vn.backend_2208.dto.UserDto;
import t3h.vn.backend_2208.entities.UserEntity;
import t3h.vn.backend_2208.repositories.user.UserRepository;
import t3h.vn.backend_2208.service.gmail.EmailDetails;
import t3h.vn.backend_2208.service.gmail.EmailService1;

@Service// đanh dấu nó là service (1 bean trong spring bean container)
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService1 emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    //1: Sử dụng Spring data JPA để thêm sửa xóa, lấy danh sách bảng user và book

    public ResponseDto save(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        if (userDto.getId() != null){
            userEntity = userRepository.findById(userDto.getId()).get();
        } else {
            userEntity.setUsername(userDto.getUserName());
            userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        userEntity.setFullName(userDto.getFullName());
        userEntity.setRole(userDto.getRole());
        userEntity.setStatus(userDto.getStatus());
        userEntity.setAddress(userDto.getAddress());
        userRepository.save(userEntity);
        return new ResponseDto("Lưu tài khoản " + userDto.getFullName() + " thành công");
    }

    public void list(ResponseTableDto tableDto) {
        tableDto.list(userRepository);
    }

    public UserEntity detail(Long id) {
       UserEntity detail = userRepository.findById(id).get();;
        return detail;
    }

    public String delete(Long id) {
        UserEntity detail = userRepository.findById(id).get();
        if (detail == null) return "Tài khoản không tồn tại";
        userRepository.deleteById(id);
        return "Xóa thành công";
    }

    public ResponseDto register(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUserName());
        userEntity.setRole("CUSTOMER");
        userEntity.setStatus(0);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(userDto.getUserName());
        emailDetails.setSubject("[Bảo mật]XÁC NHẬN ĐĂNG KÝ TÀI KHOẢN");
        emailDetails.setMsgBody("Click vào đường link bên dưới để xác nhận đăng ký tài khoản\n" +
                "http://localhost:8080/verify/" + userEntity.getId());
        // gửi email xác nhận
        emailService.sendSimpleMail(emailDetails);
        return new ResponseDto("vui long kiểm tra email để xác nhận đăng ký tài khoản");
    }

    public String verify(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).get();
        if (userEntity == null) return "Tài khoản không tồn tại";
        if (userEntity.getStatus() != 0) return "Tài khoản đã kích hoạt";
        userEntity.setStatus(1);
        userRepository.save(userEntity);
        return "kích hoạt tài khoản thành công";
    }

    public String changePass(UserChangePassWord userChangePassWord) {
        // b1: lấy thông tin user đang đăng nhập
        Long userId = ((UserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        UserEntity userEntity = userRepository.findById(userId).get();
        // B2: kiểm tra thông tin tài khoản
        if (userEntity == null)
            return "Tài khoản đã bị xóa";
        if (!passwordEncoder.matches(userChangePassWord.getOldPassword(), userEntity.getPassword())) {
            return "Mật khẩu không đúng";
        }
        userEntity.setPassword(passwordEncoder.encode(userChangePassWord.getPassword()));
        userRepository.save(userEntity);
        // b3: logout nếu như vừa thay đổi mật khẩu
        SecurityContextHolder.getContext().setAuthentication(null);
        return "Lưu mật khẩu tài khoản thành công";
    }

}
