package shop.sabotaged.shop.security.supertokens.model.request.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifySessionRequest {
    private String accessToken;
    private boolean enableAntiCsrf;
    private boolean doAntiCsrfCheck;
    private boolean checkDatabase;
    private String antiCsrfToken;
}
