package t3h.vn.backend_2208.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders", schema = "t3h_2208", catalog = "")
@Data
public class OrderEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "TOTAL")
    private Long total;
    @Basic
    @Column(name = "USER_ID")
    private Long userId;
    @Basic
    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

}
