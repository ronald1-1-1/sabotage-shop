package shop.sabotaged.shop.dto.orderonvariant;

import lombok.AllArgsConstructor;
import lombok.Data;
import shop.sabotaged.shop.dto.variant.VariantDto;

@Data
@AllArgsConstructor
public class OrderOnVariantDto {

    private Long orderId;
    private Long variantId;
    private Integer amount;
}
