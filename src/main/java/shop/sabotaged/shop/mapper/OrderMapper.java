package shop.sabotaged.shop.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import shop.sabotaged.shop.dto.order.CreateOrderDto;
import shop.sabotaged.shop.dto.order.OrderDto;
import shop.sabotaged.shop.dto.orderonvariant.OrderOnVariantDto;
import shop.sabotaged.shop.enitity.OrderEntity;

@Component
@RequiredArgsConstructor
public class OrderMapper implements Mapper<OrderDto, OrderEntity> {

    private final ModelMapper modelMapper;

    @Override
    public OrderDto toDtoFromEntity(OrderEntity orderEntity) {
        OrderDto orderDto = modelMapper.map(orderEntity, OrderDto.class);
        orderDto.setVariants(orderEntity.getVariants().stream()
                .map(orderOnVariantEntity -> new OrderOnVariantDto(
                        orderOnVariantEntity.getVariant().getId(),
                        orderOnVariantEntity.getOrder().getId(),
                        orderOnVariantEntity.getAmount()
                )).toList());
        return orderDto;
    }

}
