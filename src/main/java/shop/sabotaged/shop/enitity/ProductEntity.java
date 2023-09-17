package shop.sabotaged.shop.enitity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products_tb")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    private String vendorCode;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean show;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<VariantEntity> variants;

}
