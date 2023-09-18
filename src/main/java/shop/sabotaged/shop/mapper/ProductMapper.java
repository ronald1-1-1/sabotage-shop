package shop.sabotaged.shop.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import shop.sabotaged.shop.dto.product.CreateProductDto;
import shop.sabotaged.shop.dto.product.ProductDto;
import shop.sabotaged.shop.dto.variant.VariantDto;
import shop.sabotaged.shop.enitity.ProductEntity;
import shop.sabotaged.shop.enitity.VariantEntity;

@Component
@RequiredArgsConstructor
public class ProductMapper implements Mapper<ProductDto, ProductEntity> {

    private final ModelMapper modelMapper;

    @Override
    public ProductDto toDtoFromEntity(ProductEntity productEntity) {
        ProductDto productDto = modelMapper.map(productEntity, ProductDto.class);
        productDto.setVariants(productEntity.getVariants().stream()
                .map(variantEntity -> VariantDto.builder()
                        .id(variantEntity.getId())
                        .amount(variantEntity.getAmount())
                        .name(variantEntity.getName())
                        .show(variantEntity.getShow())
                        .build()).toList());
        return productDto;
    }

}
