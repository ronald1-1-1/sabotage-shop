package shop.sabotaged.shop.dto.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import shop.sabotaged.shop.dto.orderonvariant.CreateOrderOnVariantDto;
import shop.sabotaged.shop.enitity.OrderOnVariantEntity;
import java.util.List;

@Data
public class CreateOrderDto {

    @NotNull
    @Valid
    private List<CreateOrderOnVariantDto> variants;
}
