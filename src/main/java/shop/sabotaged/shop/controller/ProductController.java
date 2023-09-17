package shop.sabotaged.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.sabotaged.shop.dto.product.CreateProductDto;
import shop.sabotaged.shop.dto.product.ProductDto;
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

    @PostMapping
    public ProductDto create(@RequestBody CreateProductDto createProductDto) {
        return productService.createProduct(createProductDto);
    }
}
