package t3h.vn.backend_2208.entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product_order", schema = "t3h_2208", catalog = "")
@Data
public class ProductOrderEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    @Column(name = "ORDER_ID")
    private Long orderId;


    @Basic
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Basic
    @Column(name = "USER_ID")
    private Long userId;

    @Basic
    @Column(name = "color")
    private String color;

    @Basic
    @Column(name = "size")
    private String size;

    @Basic
    @Column(name = "number")
    private int number;

    @Basic
    @Column(name = "status")
    private int status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    ProductsEntity productsEntity;
}
