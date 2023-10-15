package shop.sabotaged.shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shop.sabotaged.shop.dto.variantchanges.CreateVariantChangesDto;
import shop.sabotaged.shop.dto.variantchanges.VariantChangesDto;
import shop.sabotaged.shop.service.VariantChangesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/variantChanges")
public class VariantChangesController {

    private final VariantChangesService variantChangesService;

    @GetMapping
    @PreAuthorize("isAuthenticated() && hasAuthority(T(shop.sabotaged.shop.security.UserRole).ADMIN.role)")
    public List<VariantChangesDto> getAll() {
        return variantChangesService.getAll();
    }


    @GetMapping("/variant/{variantId}")
    @PreAuthorize("isAuthenticated() && hasAuthority(T(shop.sabotaged.shop.security.UserRole).ADMIN.role)")
    public List<VariantChangesDto> getAllForVariant(@PathVariable Long variantId) {
        return variantChangesService.getAllForVariant(variantId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() && hasAuthority(T(shop.sabotaged.shop.security.UserRole).ADMIN.role)")
    public VariantChangesDto get(@PathVariable Long id) {
        return variantChangesService.get(id);
    }


    @PostMapping
    @PreAuthorize("isAuthenticated() && hasAuthority(T(shop.sabotaged.shop.security.UserRole).ADMIN.role)")
    public VariantChangesDto create(@Valid @RequestBody CreateVariantChangesDto createVariantChangesDto) {
        return variantChangesService.create(createVariantChangesDto);
    }
}
