package shop.sabotaged.shop.enitity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "variant_changes_tb")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariantChangesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer amountDifference;

    @Column
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(nullable = false)
    private VariantEntity variant;
}
