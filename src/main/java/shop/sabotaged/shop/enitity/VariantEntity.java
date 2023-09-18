package shop.sabotaged.shop.enitity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "variants_tb")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ProductEntity product;

    @OneToMany(mappedBy = "variant")
    private List<OrderOnVariantEntity> orders;

    @OneToMany(mappedBy = "variant")
    private List<VariantChangesEntity> variantChanges;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Boolean show;
}
