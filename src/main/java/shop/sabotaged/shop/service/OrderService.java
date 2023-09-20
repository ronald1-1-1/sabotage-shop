package shop.sabotaged.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.sabotaged.shop.dto.order.CreateOrderDto;
import shop.sabotaged.shop.dto.order.OrderDto;
import shop.sabotaged.shop.enitity.OrderEntity;
import shop.sabotaged.shop.enitity.OrderOnVariantEntity;
import shop.sabotaged.shop.enitity.VariantEntity;
import shop.sabotaged.shop.exception.NotEnoughVariantAmountException;
import shop.sabotaged.shop.exception.ObjectNotFoundException;
import shop.sabotaged.shop.mapper.OrderMapper;
import shop.sabotaged.shop.repository.OrderRepository;
import shop.sabotaged.shop.repository.VariantRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final VariantRepository variantRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDtoFromEntity).toList();
    }

    public OrderDto get(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Order", id));
        return orderMapper.toDtoFromEntity(orderEntity);
    }

    @Transactional(readOnly = false)
    public OrderDto create(CreateOrderDto createOrderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate(new Date());
        orderEntity.setVariants(createOrderDto.getVariants().stream()
                .map(createOrderOnVariantDto -> OrderOnVariantEntity.builder()
                        .variant(variantRepository.findById(createOrderOnVariantDto.getVariantId())
                                    .orElseThrow(() -> new ObjectNotFoundException(
                                            "Variant",
                                            createOrderOnVariantDto.getVariantId())))
                        .amount(createOrderOnVariantDto.getAmount())
                        .build()).toList());
        orderEntity.getVariants().forEach(orderOnVariantEntity -> {
            VariantEntity variantEntity = orderOnVariantEntity.getVariant();
            variantEntity.setAmount(variantEntity.getAmount() - orderOnVariantEntity.getAmount());
            if (variantEntity.getAmount() < 0) {
                throw new NotEnoughVariantAmountException(variantEntity.getId());
            }
            variantRepository.save(variantEntity);
        });
        orderEntity = orderRepository.save(orderEntity);
        return orderMapper.toDtoFromEntity(orderEntity);
    }

}
