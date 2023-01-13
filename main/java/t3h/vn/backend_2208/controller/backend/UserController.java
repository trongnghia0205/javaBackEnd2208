package t3h.vn.backend_2208.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import t3h.vn.backend_2208.config.paging.PagingParam;
import t3h.vn.backend_2208.dto.ResponseDto;
import t3h.vn.backend_2208.dto.ResponseTableDto;
import t3h.vn.backend_2208.dto.UserChangePassWord;
import t3h.vn.backend_2208.dto.UserDto;
import t3h.vn.backend_2208.service.UserService;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("backend/user")
public class UserController {

    @Autowired
    UserService userService;

    // Danh sách user chỉ nhưng tài khoản có quyền ADMIN mới được truy cập
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("list")
    public String list(@PagingParam(path = "user")ResponseTableDto responseTableDto) {
        userService.list(responseTableDto);
        return "/user/list";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("create")
    public String create(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "user/create";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.detail(id));
        return "user/detail";
    }


    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.detail(id));
        return "user/edit";
    }

    @PostMapping(value = "save",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String save(@Valid UserDto userDto, BindingResult bindingResult,
                       Model model, RedirectAttributes redirectAttributes)
            throws SQLException {
        if (bindingResult.hasErrors()) {
            return "user/create";
        } else if (!userDto.getPassword().equalsIgnoreCase(userDto.getRePassword())) {
            bindingResult.rejectValue("rePassword", "error.userDto", "Mật khẩu không khớp");
            return "user/create";
        }
        ResponseDto dto = userService.save(userDto);
        redirectAttributes.addFlashAttribute("message", dto.getMessage());
        return "redirect:/backend/user/list";

//        return "/jsp/user/create.jsp";
    }

    @PostMapping(value = "update",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String update(UserDto userDto, RedirectAttributes redirectAttributes)
            throws SQLException {
        ResponseDto dto = userService.save(userDto);
        redirectAttributes.addFlashAttribute("message", dto.getMessage());
        return "redirect:/backend/user/list";

//        return "/jsp/user/create.jsp";
    }


    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public String delete( @PathVariable Long id)
            throws SQLException {
        return userService.delete(id);
    }

    @GetMapping("info")
    public String info(Model model) {
        return "user/info";
    }

    @GetMapping("changepass")
    public String changepass(Model model) {
        model.addAttribute("user", new UserChangePassWord());
        return "user/changepass";
    }

    @PostMapping(value = "changepass",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveChangepass(@Valid @ModelAttribute("user") UserChangePassWord userDto, BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes)
            throws SQLException {
        if (bindingResult.hasErrors()) {
            return "user/changepass";
        } else if (!userDto.getPassword().equalsIgnoreCase(userDto.getRePassword())) {
            bindingResult.rejectValue("rePassword", "error.userDto", "Mật khẩu không khớp");
            return "user/changepass";
        }
        String dto = userService.changePass(userDto);
        redirectAttributes.addFlashAttribute("message", dto);
        return "redirect:/backend/user/info";

//        return "/jsp/user/create.jsp";
    }
    // để validate bằng spring validation thì:
    // Bước 1: thêm thư viện maven spring-boot-starter-validation
    // Bước 2: Custom Dto định nghĩa, rule để validate và message
    // Bước 3: Thêm @Valid và @ModelAttribute trên controller
    // thêm BindingResult ngay sau dto
    // Bước 4: Sử dụng taglib form của spring
    // Bài tập: Tạo mới tài khoản: nếu đã tồn tại tài khoản có trùng email(username) thì sẽ thông báo lỗi ra màn hình

}
