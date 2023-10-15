package shop.sabotaged.shop.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.sabotaged.shop.dto.auth.TokensDto;
import shop.sabotaged.shop.security.UserRole;
import shop.sabotaged.shop.security.supertokens.SuperTokensEmailPasswordManager;
import shop.sabotaged.shop.security.supertokens.SuperTokensRoleManager;
import shop.sabotaged.shop.security.supertokens.SuperTokensSessionManager;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SuperTokensEmailPasswordManager superTokensEmailPasswordManager;
    private final SuperTokensSessionManager superTokensSessionManager;
    private final SuperTokensRoleManager superTokensRoleManager;

    public TokensDto signIn(String email, String password) {
        String userId = superTokensEmailPasswordManager.signIn(email, password);
        return superTokensSessionManager.createSession(userId, email);
    }

    public TokensDto refreshToken(String refreshToken) {
        return superTokensSessionManager.refreshSession(refreshToken);
    }

    public TokensDto signUp(String email, String password) {
        String userId = superTokensEmailPasswordManager.signUp(email, password);
        superTokensRoleManager.addRoleToUser(userId, UserRole.USER.getRole());
        return superTokensSessionManager.createSession(userId, email);
    }

    public void signOut(String sessionHandle) {
        superTokensSessionManager.removeSession(sessionHandle);
    }

}
