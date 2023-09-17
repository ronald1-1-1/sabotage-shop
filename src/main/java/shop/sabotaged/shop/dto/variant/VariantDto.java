package shop.sabotaged.shop.dto.variant;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.sabotaged.shop.dto.orderonvariant.OrderOnVariantDto;
import shop.sabotaged.shop.dto.variantchanges.VariantChangesDto;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VariantDto {

    private Long id;
    private String productVendorCode;
    private List<OrderOnVariantDto> orders;
    private List<VariantChangesDto> variantChanges;
    private Integer amount;
    private String name;
}
