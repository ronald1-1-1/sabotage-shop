package shop.sabotaged.shop.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import shop.sabotaged.shop.dto.orderonvariant.OrderOnVariantDto;
import shop.sabotaged.shop.dto.variant.CreateVariantDto;
import shop.sabotaged.shop.dto.variant.VariantDto;
import shop.sabotaged.shop.dto.variantchanges.VariantChangesDto;
import shop.sabotaged.shop.enitity.VariantEntity;

@Component
@RequiredArgsConstructor
public class VariantMapper implements Mapper<VariantDto, VariantEntity>{

    private final ModelMapper modelMapper;

    @Override
    public VariantDto toDtoFromEntity(VariantEntity variantEntity) {
        VariantDto variantDto = modelMapper.map(variantEntity, VariantDto.class);
        variantDto.setOrders(variantEntity.getOrders().stream()
                .map(orderOnVariantEntity -> new OrderOnVariantDto(
                        orderOnVariantEntity.getVariant().getId(),
                        orderOnVariantEntity.getOrder().getId(),
                        orderOnVariantEntity.getAmount()
                )).toList());
        variantDto.setVariantChanges(variantEntity.getVariantChanges().stream()
                .map(variantChangesEntity -> modelMapper.map(variantChangesEntity, VariantChangesDto.class)).toList());
        variantDto.setProductVendorCode(variantEntity.getProduct().getVendorCode());
        return variantDto;
    }


}
