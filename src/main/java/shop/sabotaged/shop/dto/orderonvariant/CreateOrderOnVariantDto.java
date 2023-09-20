package shop.sabotaged.shop.dto.orderonvariant;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderOnVariantDto {

    @NotNull
    private Long variantId;

    @NotNull
    private Integer amount;
}
