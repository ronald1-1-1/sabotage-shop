package shop.sabotaged.shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.sabotaged.shop.dto.order.CreateOrderDto;
import shop.sabotaged.shop.dto.order.OrderDto;
import shop.sabotaged.shop.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }


    @PostMapping
    public OrderDto create(@Valid @RequestBody CreateOrderDto createOrderDto) {
        return orderService.create(createOrderDto);
    }

}
