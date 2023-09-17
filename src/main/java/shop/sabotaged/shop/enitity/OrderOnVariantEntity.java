package shop.sabotaged.shop.enitity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_on_variant_tb")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderOnVariantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private VariantEntity variant;

    @ManyToOne
    @JoinColumn(nullable = false)
    private OrderEntity order;


    @Column(nullable = false)
    private Integer amount;

}
