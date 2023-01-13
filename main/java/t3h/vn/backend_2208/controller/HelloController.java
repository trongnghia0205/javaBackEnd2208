package t3h.vn.backend_2208.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import t3h.vn.backend_2208.db.DbUtils;
import t3h.vn.backend_2208.dto.Book;

import java.sql.SQLException;

@RestController// = @Controller + @@ResponseBody
// Giao thức http, rest api
// định nghĩa đây là 1 controller
public class HelloController {
    // 1: Thêm mới sách thông qua api
    // 2: Cập nhật sách thông qua api dựa vào thuộc tính ID của sách
    // 3: Lấy thông tin chi tiết sách dựa vào ID sách
    // 4: xóa sách dựa vào ID sách
    // 5: Lấy danh sách sách và tìm kiếm theo tên tác giả hoặc tên sách
    // (NAME like '%name%' or AUTHOR like '%name%' )

    // mapping đường dẫn controller
//    @RequestMapping(method = RequestMethod.GET, value = "get")
    @GetMapping("get")// sử dụng để lấy thông tin
    public String get(){
        return "get";
    }
    @GetMapping("get/{id}")// sử dụng để lấy thông tin
    public Book getId(@PathVariable Long id,
                        @RequestParam(required = false) String name) throws SQLException {
        return DbUtils.getAllById(id);
    }
    @PostMapping(value = "post",
            consumes = MediaType.APPLICATION_JSON_VALUE)// sử dụng trong phần tạo mới dữ liệu
    public String post(@RequestBody Book body) throws SQLException {
        DbUtils.insertBook(body);
        return "Thêm mới thành công";
    }

    @PostMapping(value = "post2",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String post2(Book body) throws SQLException {
        DbUtils.insertBook(body);
        return "Thêm mới thành công";
    }

    @PutMapping("put")// sử dụng trong phần sửa dữ liệu
    public String put(){
        return "put";
    }
    @DeleteMapping("delete")// sử dụng trong phần xóa dữ liệu
    public String delete(){
        return "delete";
    }

    @GetMapping("getAll")// sử dụng để lấy thông tin
    public Object getAll(@RequestParam String name, @RequestParam String author) throws SQLException {
        return DbUtils.getAll(name, author);
    }

    @GetMapping("search")// sử dụng để lấy thông tin
    public Object search(@RequestParam String key) throws SQLException {
        return DbUtils.getAll(key);
    }

}
