package t3h.vn.backend_2208.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import t3h.vn.backend_2208.db.DbUtils;
import t3h.vn.backend_2208.dto.Book;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("book")
public class BookController {

    // 1: làm lại theo bài giảng
    // 2: Tạo giao diện tạo mới book và
    // khi click tạo nut tạo mới thì sẽ gọi api
    // và lưu vào database
    @GetMapping("create")
    public String create() throws SQLException {
        return "/jsp/book/create.jsp";
    }

    @PostMapping(value = "save",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String save(Book book, Model model, RedirectAttributes redirectAttributes) throws SQLException {
        DbUtils.insertBook(book);
        // Khi tạo mới và trả về kết quả thì sẽ không sử dụng return đường dẫn jsp
//        model.addAttribute("message", "Tạo mới thành công");
//        return "/jsp/book/create.jsp";

        redirectAttributes.addFlashAttribute("message", "Tạo mới thành công");
        return "redirect:/book/create";
    }


    @GetMapping("detail/{id}")
    public String detail(@PathVariable Long id, Model model) throws SQLException {
        Book book = DbUtils.getAllById(id);
        model.addAttribute("b", book);
        return "/jsp/book/detail.jsp";
    }

    @GetMapping("list")
    public String list(Model model,
                       @RequestParam(required = false) String key) throws SQLException {
        List<Book> bookList = DbUtils.getAll(key);
        model.addAttribute("list", bookList);
        return "/jsp/book/list.jsp";
    }

//    @GetMapping("list")
//    public String list(Model model,
//                       @RequestParam(required = false) String author,
//                       @RequestParam(required = false) String name) throws SQLException {
//        List<Book> bookList = DbUtils.getAll(name, author);
//        model.addAttribute("list", bookList);
//        return "/jsp/book/list.jsp";
//    }

//     Bài tập
//     1: tạo mới bảng USER(ID, USER_NAME, PASSWORD, ADDRESS, FULL_NAME, STATUS, ROLE)
//     ID: tự động tăng, USER_NAME + PASSWORD, STATUS(int, 1:kích hoạt, 0 tạm dừng), ROLE(ADMIN, USER)
//     2: Tạo controller và service cho user để thực hiện chức năng
//      a: Tạo mới user
//      b: Danh sách user
//      c: Hiển thị thông tin chi tiết user theo ID

//     3: Thực hiện validation thông tin user khi tạo mới(user name là định dạng email,
//     nhập mật khẩu phai có nhập lại mật khẩu phải trùng nhau, full name không được phép rỗng)
//     4: Vẽ sơ đồ luồng khi sử dụng cú phap redirect trong springboot
}
