package t3h.vn.backend_2208.db;

import t3h.vn.backend_2208.dto.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserUtils {
    static String DB_INFO = "jdbc:mysql://localhost:3306/t3h_2208";
    static String USER_NAME = "root";
    static String PASS = "1234";


    public static boolean checkExistByEmail(String email) throws SQLException {
        boolean result = false;
        List<Book> bookList = new ArrayList<>();
        // Bước 1: Tạo kết nối đến csdl
        Connection conn = DriverManager.getConnection(DB_INFO, USER_NAME, PASS);
        // Bước 2: Tạo đối Statement từ đối tượng Connection Để viết câu lệnh truy vấn
        // Câu lện tương tác với csdl
        Statement sqlStatement = conn.createStatement();
        String selectSql = "SELECT * FROM user where USER_NAME = '" + email + "'";
        // Bước 3: Thực thi câu lệnh lên sql server -> kết quả trả về ResultSet
        ResultSet ketQua = sqlStatement.executeQuery(selectSql);
        // Bước 4: Xử lý kết quả trả về (hiển thị lên màn hình)
        while (ketQua.next()) {
            result = true;
        }
        // Bước 5: đóng kết nối
        sqlStatement.close();
        conn.close();
        return result;
    }
}
