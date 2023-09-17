package shop.sabotaged.shop.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import shop.sabotaged.shop.dto.variantchanges.CreateVariantChangesDto;
import shop.sabotaged.shop.dto.variantchanges.VariantChangesDto;
import shop.sabotaged.shop.enitity.VariantChangesEntity;

@Component
@RequiredArgsConstructor
public class VariantChangesMapper{

    private final ModelMapper modelMapper;

    public VariantChangesDto toDtoFromEntity(VariantChangesEntity variantChangesEntity) {
        VariantChangesDto variantChangesDto = modelMapper.map(variantChangesEntity, VariantChangesDto.class);
        variantChangesDto.setVariantId(variantChangesEntity.getVariant().getId());
        return variantChangesDto;
    }

    public VariantChangesEntity toEntityFromCreateDto(CreateVariantChangesDto dto) {
        return null;
    }

}
