package shop.sabotaged.shop.dto.variantchanges;

import jakarta.persistence.*;
import lombok.Data;
import shop.sabotaged.shop.dto.variant.VariantDto;
import shop.sabotaged.shop.enitity.VariantEntity;

import java.util.Date;

@Data
public class VariantChangesDto {

    private Long id;
    private Integer amountDifference;
    private String comment;
    private Date date;
    private Long variantId;
}
