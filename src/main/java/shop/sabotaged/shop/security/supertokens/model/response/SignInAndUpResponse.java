package shop.sabotaged.shop.security.supertokens.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import shop.sabotaged.shop.security.supertokens.model.type.User;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SignInAndUpResponse extends StatusResponse {
    private User user;
    private String recipeUserId;
}
