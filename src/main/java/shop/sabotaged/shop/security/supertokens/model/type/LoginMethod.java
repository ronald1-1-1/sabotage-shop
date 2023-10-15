package shop.sabotaged.shop.security.supertokens.model.type;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LoginMethod {
    private List<String> tenantIds;
    private String recipeUserId;
    private boolean verified;
    private long timeJoined;
    private String recipeId;
    private String email;
}
