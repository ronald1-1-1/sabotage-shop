package shop.sabotaged.shop.security.supertokens.model.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String handle;
    private String userId;
    private Object userDataInJWT;
    private String tenantId;
    private String recipeUserId;
}
