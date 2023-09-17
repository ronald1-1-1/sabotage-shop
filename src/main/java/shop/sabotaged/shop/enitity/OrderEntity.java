package shop.sabotaged.shop.enitity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders_tb")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order")
    private List<OrderOnVariantEntity> variants;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;
}
