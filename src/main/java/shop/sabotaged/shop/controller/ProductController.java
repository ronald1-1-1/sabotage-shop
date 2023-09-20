package shop.sabotaged.shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.sabotaged.shop.dto.product.CreateProductDto;
import shop.sabotaged.shop.dto.product.ProductDto;
import shop.sabotaged.shop.dto.product.UpdateProductDto;
import shop.sabotaged.shop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{vendorCode}")
    public ProductDto get(@PathVariable String vendorCode) {
        return productService.get(vendorCode);
    }

    @PostMapping
    public ProductDto create(@Valid @RequestBody CreateProductDto createProductDto) {
        return productService.create(createProductDto);
    }

    @PutMapping
    public ProductDto update(@Valid @RequestBody UpdateProductDto updateProductDto) {
        return productService.update(updateProductDto);
    }
}
