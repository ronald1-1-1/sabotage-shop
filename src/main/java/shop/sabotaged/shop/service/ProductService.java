package shop.sabotaged.shop.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import shop.sabotaged.shop.dto.product.CreateProductDto;
import shop.sabotaged.shop.dto.product.ProductDto;
import shop.sabotaged.shop.enitity.ProductEntity;
import shop.sabotaged.shop.mapper.ProductMapper;
import shop.sabotaged.shop.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDtoFromEntity).toList();
    }

    public ProductDto createProduct(CreateProductDto createProductDto) {
        ProductEntity productEntity = productMapper.toEntityFromCreateDto(createProductDto);
        productEntity = productRepository.save(productEntity);
        return productMapper.toDtoFromEntity(productEntity);
    }
}
