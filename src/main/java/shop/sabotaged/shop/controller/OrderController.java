package shop.sabotaged.shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import shop.sabotaged.shop.dto.order.CreateOrderDto;
import shop.sabotaged.shop.dto.order.OrderDto;
import shop.sabotaged.shop.security.SuperTokensPrincipal;
import shop.sabotaged.shop.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("isAuthenticated() && hasAuthority(T(shop.sabotaged.shop.security.UserRole).ADMIN.role)")
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }


    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public OrderDto create(@Valid @RequestBody CreateOrderDto createOrderDto,
                           @AuthenticationPrincipal SuperTokensPrincipal principal) {
        return orderService.create(createOrderDto);
    }

}
