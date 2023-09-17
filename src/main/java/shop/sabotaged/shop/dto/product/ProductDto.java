package shop.sabotaged.shop.dto.product;

import lombok.Data;
import org.modelmapper.ModelMapper;
import shop.sabotaged.shop.dto.variant.VariantDto;
import shop.sabotaged.shop.enitity.ProductEntity;

import java.util.List;

@Data
public class ProductDto {

    private String vendorCode;
    private Float price;
    private String name;
    private String description;
    private Boolean show;
    private List<VariantDto> variants;
}
