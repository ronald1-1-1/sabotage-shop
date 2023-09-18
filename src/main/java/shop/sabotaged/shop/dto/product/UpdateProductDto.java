package shop.sabotaged.shop.dto.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import shop.sabotaged.shop.dto.variant.UpdateVariantDto;

import java.util.List;

@Data
public class UpdateProductDto {

    @NotBlank
    private String vendorCode;

    @NotNull
    private Float price;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Boolean show;

    @NotNull
    @Valid
    private List<UpdateVariantDto> variants;
}
