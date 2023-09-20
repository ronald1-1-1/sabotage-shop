package shop.sabotaged.shop.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.sabotaged.shop.dto.variantchanges.CreateVariantChangesDto;
import shop.sabotaged.shop.dto.variantchanges.VariantChangesDto;
import shop.sabotaged.shop.enitity.VariantChangesEntity;
import shop.sabotaged.shop.enitity.VariantEntity;
import shop.sabotaged.shop.exception.NotEnoughVariantAmountException;
import shop.sabotaged.shop.exception.ObjectNotFoundException;
import shop.sabotaged.shop.mapper.VariantChangesMapper;
import shop.sabotaged.shop.repository.VariantChangesRepository;
import shop.sabotaged.shop.repository.VariantRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VariantChangesService {

    private final VariantChangesRepository variantChangesRepository;
    private final VariantChangesMapper variantChangesMapper;
    private final VariantRepository variantRepository;
    private final ModelMapper modelMapper;

    public List<VariantChangesDto> getAll() {
        return variantChangesRepository.findAll().stream()
                .map(variantChangesMapper::toDtoFromEntity).toList();
    }

    public List<VariantChangesDto> getAllForVariant(Long variantId) {
        VariantEntity variantEntity = variantRepository.findById(variantId)
                .orElseThrow(() -> new ObjectNotFoundException("Variant", variantId));

        return variantEntity.getVariantChanges().stream()
                .map(variantChangesMapper::toDtoFromEntity).toList();
    }

    public VariantChangesDto get(Long id) {
        VariantChangesEntity variantChangesEntity = variantChangesRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Variant changes", id));
        return variantChangesMapper.toDtoFromEntity(variantChangesEntity);
    }

    @Transactional(readOnly = false)
    public VariantChangesDto create(CreateVariantChangesDto createVariantChangesDto) {
        VariantEntity variantEntity = variantRepository.findById(createVariantChangesDto.getVariantId())
                .orElseThrow(() -> new ObjectNotFoundException("Variant", createVariantChangesDto.getVariantId()));

        variantEntity.setAmount(variantEntity.getAmount() + createVariantChangesDto.getAmountDifference());
        if (variantEntity.getAmount() < 0) {
            throw new NotEnoughVariantAmountException(variantEntity.getId());
        }

        VariantChangesEntity variantChangesEntity = modelMapper.map(
                createVariantChangesDto,
                VariantChangesEntity.class);
        variantChangesEntity.setVariant(variantEntity);
        variantChangesEntity.setDate(new Date());

        variantRepository.save(variantEntity);
        variantChangesEntity = variantChangesRepository.save(variantChangesEntity);

        return variantChangesMapper.toDtoFromEntity(variantChangesEntity);
    }
}
