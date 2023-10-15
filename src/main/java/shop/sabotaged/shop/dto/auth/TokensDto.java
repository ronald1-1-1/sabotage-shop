package shop.sabotaged.shop.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokensDto {
    public String accessToken;
    public String refreshToken;
}
