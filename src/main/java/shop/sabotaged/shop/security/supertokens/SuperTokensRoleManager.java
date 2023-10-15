package shop.sabotaged.shop.security.supertokens;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shop.sabotaged.shop.security.supertokens.model.query.UserIdQuery;
import shop.sabotaged.shop.security.supertokens.model.request.role.AddRoleToUserRequest;
import shop.sabotaged.shop.security.supertokens.model.response.role.UserRolesResponse;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SuperTokensRoleManager {

    private static final String ADD_ROLE_TO_USER_PATH = "/public/recipe/user/role";
    private static final String GET_USER_ROLES_PATH = "/public/recipe/user/roles";

    private final SuperTokensRequestManager superTokensRequestManager;

    public void addRoleToUser(String userId, String role) {
        AddRoleToUserRequest addRoleToUserRequest = new AddRoleToUserRequest(userId, role);
        superTokensRequestManager.put(ADD_ROLE_TO_USER_PATH, addRoleToUserRequest);
    }

    public List<String> getUserRoles(String userId) {
        UserIdQuery userIdQuery = new UserIdQuery(userId);
        UserRolesResponse userRolesResponse =
                superTokensRequestManager.get(GET_USER_ROLES_PATH, userIdQuery, UserRolesResponse.class);
        return userRolesResponse.getRoles();
    }
}
