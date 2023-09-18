package shop.sabotaged.shop.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import shop.sabotaged.shop.dto.variantchanges.CreateVariantChangesDto;
import shop.sabotaged.shop.dto.variantchanges.VariantChangesDto;
import shop.sabotaged.shop.enitity.VariantChangesEntity;

@Component
@RequiredArgsConstructor
public class VariantChangesMapper implements Mapper<VariantChangesDto, VariantChangesEntity> {

    private final ModelMapper modelMapper;

    @Override
    public VariantChangesDto toDtoFromEntity(VariantChangesEntity variantChangesEntity) {
        VariantChangesDto variantChangesDto = modelMapper.map(variantChangesEntity, VariantChangesDto.class);
        variantChangesDto.setVariantId(variantChangesEntity.getVariant().getId());
        return variantChangesDto;
    }


}
