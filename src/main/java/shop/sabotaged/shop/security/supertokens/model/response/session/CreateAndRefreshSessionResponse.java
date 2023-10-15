package shop.sabotaged.shop.security.supertokens.model.response.session;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import shop.sabotaged.shop.security.supertokens.model.response.StatusResponse;
import shop.sabotaged.shop.security.supertokens.model.type.Session;
import shop.sabotaged.shop.security.supertokens.model.type.Token;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateAndRefreshSessionResponse extends StatusResponse {
    private Session session;
    private Token accessToken;
    private Token refreshToken;
    private String antiCsrfToken;
}
