package t3h.vn.backend_2208.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data// tự động gen ra hàm getter setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    Long id;
    String name;
    String author;

}
