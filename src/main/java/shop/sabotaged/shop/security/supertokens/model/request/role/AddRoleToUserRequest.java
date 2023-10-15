package shop.sabotaged.shop.security.supertokens.model.request.role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddRoleToUserRequest {
    private String userId;
    private String role;
}
