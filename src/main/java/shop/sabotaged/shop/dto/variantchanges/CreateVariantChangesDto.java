package shop.sabotaged.shop.dto.variantchanges;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CreateVariantChangesDto {

    @NotNull
    private Integer amountDifference;

    private String comment;

    @NotNull
    private Long variantId;
}
