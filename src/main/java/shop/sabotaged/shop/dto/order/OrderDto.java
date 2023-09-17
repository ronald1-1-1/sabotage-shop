package shop.sabotaged.shop.dto.order;

import lombok.Data;
import shop.sabotaged.shop.dto.orderonvariant.OrderOnVariantDto;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    private Long id;
    private List<OrderOnVariantDto> variants;
    private Date date;
}
