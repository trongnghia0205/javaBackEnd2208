package t3h.vn.backend_2208.db;

import t3h.vn.backend_2208.dto.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbUtils {
    static String DB_INFO = "jdbc:mysql://localhost:3306/t3h_2208";
    static String USER_NAME = "root";
    static String PASS = "1234";

    public static void insertBook(Book book)  throws SQLException {
        // Bước 1: Tạo kết nối đến csdl
        Connection conn = DriverManager.getConnection(DB_INFO, USER_NAME, PASS);
        // Bước 2: Tạo đối Statement từ đối tượng Connection Để viết câu lệnh truy vấn
        // Câu lện tương tác với csdl
        String insertSql = "INSERT INTO sach (NAME, AUTHOR) VALUES(?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.execute();
//        Statement sqlStatement = conn.createStatement();
//        String insertSql = "INSERT INTO sach (NAME, AUTHOR) VALUES('" + name + "','" + author +"')";
//        // Bước 3: Thực thi câu lệnh lên sql server -> kết quả trả về ResultSet
//        sqlStatement.execute(insertSql);
        // Bước 4: Xử lý kết quả trả về (hiển thị lên màn hình)
        // Bước 5: đóng kết nối
//        sqlStatement.close();
        conn.close();
    }

    public static Book getAllById(Long id)  throws SQLException {
        Book book = null;
        // Bước 1: Tạo kết nối đến csdl
        Connection conn = DriverManager.getConnection(DB_INFO, USER_NAME, PASS);
        // Bước 2: Tạo đối Statement từ đối tượng Connection Để viết câu lệnh truy vấn
        // Câu lện tương tác với csdl
        Statement sqlStatement = conn.createStatement();
        String selectSql = "SELECT * FROM sach where ID =" + id;
        // Bước 3: Thực thi câu lệnh lên sql server -> kết quả trả về ResultSet
        ResultSet ketQua = sqlStatement.executeQuery(selectSql);
        // Bước 4: Xử lý kết quả trả về (hiển thị lên màn hình)
        while (ketQua.next()) {
            Long id_ = ketQua.getLong("ID");
            String monHoc = ketQua.getString("NAME");
            String tacGia = ketQua.getString("AUTHOR");
            book = new Book(id_, monHoc, tacGia);
            System.out.println("ID = " + id_ + ", Môn = " + monHoc + ", Tác giả = " + tacGia);
        }
        // Bước 5: đóng kết nối
        sqlStatement.close();
        conn.close();
        return  book;
    }

    public static List<Book> getAll(String name, String author)  throws SQLException {
        List<Book> bookList = new ArrayList<>();
        // Bước 1: Tạo kết nối đến csdl
        Connection conn = DriverManager.getConnection(DB_INFO, USER_NAME, PASS);
        // Bước 2: Tạo đối Statement từ đối tượng Connection Để viết câu lệnh truy vấn
        // Câu lện tương tác với csdl
        Statement sqlStatement = conn.createStatement();
        String selectSql = "SELECT * FROM sach where 1=1";
        if (name != null && name != "") {
            selectSql += " and NAME like'%" + name + "%'";
        }
        if (author != null && author != "") {
            selectSql += " and AUTHOR like'%" + author + "%'";
        }
        // Bước 3: Thực thi câu lệnh lên sql server -> kết quả trả về ResultSet
        ResultSet ketQua = sqlStatement.executeQuery(selectSql);
        // Bước 4: Xử lý kết quả trả về (hiển thị lên màn hình)
        while (ketQua.next()) {
            Long id_ = ketQua.getLong("ID");
            String monHoc = ketQua.getString("NAME");
            String tacGia = ketQua.getString("AUTHOR");
            bookList.add(new Book(id_, monHoc, tacGia));
            System.out.println("ID = " + id_ + ", Môn = " + monHoc + ", Tác giả = " + tacGia);
        }
        // Bước 5: đóng kết nối
        sqlStatement.close();
        conn.close();
        return bookList;
    }

    public static List<Book> getAll(String key)  throws SQLException {
        List<Book> bookList = new ArrayList<>();
        // Bước 1: Tạo kết nối đến csdl
        Connection conn = DriverManager.getConnection(DB_INFO, USER_NAME, PASS);
        // Bước 2: Tạo đối Statement từ đối tượng Connection Để viết câu lệnh truy vấn
        // Câu lện tương tác với csdl
        Statement sqlStatement = conn.createStatement();
        String selectSql = "SELECT * FROM sach where 1 = 1";
        if (key != null && key != "") {
            try {
                long id = Long.parseLong(key);
                selectSql += " and (NAME like'%" + key + "%' or" + " AUTHOR like'%" + key + "%' OR ID = " + id + ")";

            } catch (Exception e) {
                selectSql += " and (NAME like'%" + key + "%' or" + " AUTHOR like'%" + key + "%')";
            }
        }
        // Bước 3: Thực thi câu lệnh lên sql server -> kết quả trả về ResultSet
        ResultSet ketQua = sqlStatement.executeQuery(selectSql);
        // Bước 4: Xử lý kết quả trả về (hiển thị lên màn hình)
        while (ketQua.next()) {
            Long id_ = ketQua.getLong("ID");
            String monHoc = ketQua.getString("NAME");
            String tacGia = ketQua.getString("AUTHOR");
            bookList.add(new Book(id_, monHoc, tacGia));
            System.out.println("ID = " + id_ + ", Môn = " + monHoc + ", Tác giả = " + tacGia);
        }
        // Bước 5: đóng kết nối
        sqlStatement.close();
        conn.close();
        return bookList;
    }
}
