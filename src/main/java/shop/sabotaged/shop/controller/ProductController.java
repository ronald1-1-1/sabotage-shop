package shop.sabotaged.shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("permitAll()")
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{vendorCode}")
    @PreAuthorize("permitAll()")
    public ProductDto get(@PathVariable String vendorCode) {
        return productService.get(vendorCode);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated() && hasAuthority(T(shop.sabotaged.shop.security.UserRole).ADMIN.role)")
    public ProductDto create(@Valid @RequestBody CreateProductDto createProductDto) {
        return productService.create(createProductDto);
    }

    @PutMapping
    @PreAuthorize("isAuthenticated() && hasAuthority(T(shop.sabotaged.shop.security.UserRole).ADMIN.role)")
    public ProductDto update(@Valid @RequestBody UpdateProductDto updateProductDto) {
        return productService.update(updateProductDto);
    }
}
