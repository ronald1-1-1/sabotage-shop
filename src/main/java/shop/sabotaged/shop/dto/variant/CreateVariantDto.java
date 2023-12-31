package shop.sabotaged.shop.dto.variant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateVariantDto {

    @NotBlank
    public String name;

    @NotNull
    public Boolean show;
}
