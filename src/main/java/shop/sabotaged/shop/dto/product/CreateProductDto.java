package shop.sabotaged.shop.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import shop.sabotaged.shop.dto.variant.CreateVariantDto;

import java.util.List;

@Data
public class CreateProductDto {
    @NotBlank
    private String vendorCode;

    @NotNull
    private Float price;

    @NotBlank
    private String name;

    private String description;
    @NotNull
    private Boolean show;

    private List<CreateVariantDto> variants;
}
