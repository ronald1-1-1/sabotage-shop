package shop.sabotaged.shop.security.supertokens.model.request.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSessionRequest {
    private String userId;
    private Object userDataInJWT;
    private Object userDataInDatabase;
    private boolean enableAntiCsrf;
}
