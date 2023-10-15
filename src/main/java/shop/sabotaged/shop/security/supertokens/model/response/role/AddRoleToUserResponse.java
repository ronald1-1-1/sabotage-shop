package shop.sabotaged.shop.security.supertokens.model.response.role;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import shop.sabotaged.shop.security.supertokens.model.response.StatusResponse;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddRoleToUserResponse extends StatusResponse {
    private boolean didUserAlreadyHaveRole;
}
