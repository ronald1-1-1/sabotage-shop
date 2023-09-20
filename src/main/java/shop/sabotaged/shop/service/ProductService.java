package shop.sabotaged.shop.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.sabotaged.shop.dto.product.CreateProductDto;
import shop.sabotaged.shop.dto.product.ProductDto;
import shop.sabotaged.shop.dto.product.UpdateProductDto;
import shop.sabotaged.shop.dto.variant.UpdateVariantDto;
import shop.sabotaged.shop.enitity.ProductEntity;
import shop.sabotaged.shop.enitity.VariantEntity;
import shop.sabotaged.shop.exception.ObjectNotFoundException;
import shop.sabotaged.shop.mapper.ProductMapper;
import shop.sabotaged.shop.repository.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ModelMapper modelMapper;

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDtoFromEntity).toList();
    }

    public ProductDto get(String vendorCode) {
        ProductEntity productEntity = productRepository.findById(vendorCode)
                .orElseThrow(() -> new ObjectNotFoundException("Product", vendorCode));
        return productMapper.toDtoFromEntity(productEntity);
    }

    @Transactional(readOnly = false)
    public ProductDto create(CreateProductDto createProductDto) {
        ProductEntity productEntity = modelMapper.map(createProductDto, ProductEntity.class);
        productEntity.setVariants(createProductDto.getVariants().stream()
                .map(createVariantDto -> VariantEntity.builder()
                        .name(createVariantDto.getName())
                        .amount(0)
                        .show(createVariantDto.getShow())
                        .build()).toList());
        productEntity = productRepository.save(productEntity);
        return productMapper.toDtoFromEntity(productEntity);
    }

    @Transactional(readOnly = false)
    public ProductDto update(UpdateProductDto updateProductDto) {
        ProductEntity productEntity = productRepository.findById(updateProductDto.getVendorCode())
                .orElseThrow(() -> new ObjectNotFoundException("Product", updateProductDto.getVendorCode()));
        productEntity.setName(updateProductDto.getName());
        productEntity.setShow(updateProductDto.getShow());
        productEntity.setPrice(updateProductDto.getPrice());
        productEntity.setShow(updateProductDto.getShow());
        productEntity.setDescription(updateProductDto.getDescription());

        Map<Long, VariantEntity> variantEntityMap = new HashMap<>();
        productEntity.getVariants().forEach(variantEntity -> variantEntityMap.put(variantEntity.getId(), variantEntity));

        List<VariantEntity> newVariants = new ArrayList<>();
        for (UpdateVariantDto updateVariantDto: updateProductDto.getVariants()) {
            if (updateVariantDto.getId() != null && variantEntityMap.containsKey(updateVariantDto.getId())) {
                VariantEntity variantEntity = variantEntityMap.get(updateVariantDto.getId());
                variantEntity.setName(updateVariantDto.getName());
                variantEntity.setShow(updateVariantDto.getShow());
            } else {
                newVariants.add(VariantEntity.builder()
                        .name(updateVariantDto.getName())
                        .amount(0)
                        .product(productEntity)
                        .show(updateProductDto.getShow())
                        .build());
            }
        }
        productEntity.getVariants().addAll(newVariants);
        productRepository.save(productEntity);
        return productMapper.toDtoFromEntity(productEntity);
    }
}
