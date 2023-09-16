package shop.sabotaged.shop.enitity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "variants_tb")
@Getter
@Setter
public class VariantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ProductEntity product;

    @OneToMany(mappedBy = "variant")
    private List<OrderEntity> orders;

    @OneToMany(mappedBy = "variant")
    private List<VariantChangesEntity> variantChanges;

    @Column
    private Integer amount;
}
