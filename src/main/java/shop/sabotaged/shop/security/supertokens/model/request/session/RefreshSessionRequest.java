package shop.sabotaged.shop.security.supertokens.model.request.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RefreshSessionRequest {
    private String refreshToken;
    private boolean enableAntiCsrf;
    private String antiCsrfToken;
}
