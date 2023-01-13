package t3h.vn.backend_2208.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "category", schema = "t3h_2208", catalog = "")
@Data
public class CategoryEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "DESCRIPTION",length = 1000)
    private String description;
    @Basic
    @Column(name = "MAIN_IMAGE")
    private String mainImage;

}
