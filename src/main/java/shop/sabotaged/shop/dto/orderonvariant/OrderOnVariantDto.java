package shop.sabotaged.shop.dto.orderonvariant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderOnVariantDto {

    private Long orderId;
    private Long variantId;
    private Integer amount;
}
