package shop.sabotaged.shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public List<VariantChangesDto> getAll() {
        return variantChangesService.getAll();
    }


    @GetMapping("/variant/{variantId}")
    public List<VariantChangesDto> getAllForVariant(@PathVariable Long variantId) {
        return variantChangesService.getAllForVariant(variantId);
    }

    @GetMapping("/{id}")
    public VariantChangesDto get(@PathVariable Long id) {
        return variantChangesService.get(id);
    }


    @PostMapping
    public VariantChangesDto create(@Valid @RequestBody CreateVariantChangesDto createVariantChangesDto) {
        return variantChangesService.create(createVariantChangesDto);
    }
}
