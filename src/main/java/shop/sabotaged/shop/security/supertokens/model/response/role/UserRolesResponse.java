package shop.sabotaged.shop.security.supertokens.model.response.role;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import shop.sabotaged.shop.security.supertokens.model.response.StatusResponse;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRolesResponse extends StatusResponse {
    private List<String> roles;
}
