package shop.sabotaged.shop.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshTokenDto {
    @NotBlank
    private String refreshToken;
}
