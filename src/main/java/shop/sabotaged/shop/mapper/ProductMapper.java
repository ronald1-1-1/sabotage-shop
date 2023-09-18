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
public class ProductMapper {

    private final ModelMapper modelMapper;

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

    public ProductEntity toEntityFromCreateDto(CreateProductDto createProductDto) {
        ProductEntity productEntity = modelMapper.map(createProductDto, ProductEntity.class);
        productEntity.setVariants(createProductDto.getVariants().stream()
                .map(createVariantDto -> VariantEntity.builder()
                        .name(createVariantDto.getName())
                        .amount(0)
                        .product(productEntity)
                        .show(createVariantDto.getShow())
                        .build()).toList());
        return productEntity;
    }



}
