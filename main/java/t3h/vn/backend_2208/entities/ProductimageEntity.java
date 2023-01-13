package t3h.vn.backend_2208.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_image", schema = "db_2206", catalog = "")
@Data
public class ProductimageEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "product_id")
    private Long productId;

    @Basic
    @Column(name = "image_url")
    private String imageUrl;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID", insertable = false, updatable = false)
//    List<ProductEntity> productEntities;

}
