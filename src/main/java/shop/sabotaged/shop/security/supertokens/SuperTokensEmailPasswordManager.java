package shop.sabotaged.shop.security.supertokens;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shop.sabotaged.shop.security.supertokens.model.request.EmailPasswordRequest;
import shop.sabotaged.shop.security.supertokens.model.response.SignInAndUpResponse;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SuperTokensEmailPasswordManager {

    private static final String SIGN_IN_PATH = "/public/recipe/signin";
    private static final String SIGN_UP_PATH = "/public/recipe/signup";

    private final SuperTokensRequestManager superTokensRequestManager;

    public String signIn(String email, String password) {
        return signInUp(email, password, SIGN_IN_PATH);
    }

    public String signUp(String email, String password) {
        return signInUp(email, password, SIGN_UP_PATH);
    }

    private String signInUp(String email, String password, String path) {
        EmailPasswordRequest emailPasswordRequest = new EmailPasswordRequest(email, password);

        SignInAndUpResponse signInUpResponse = superTokensRequestManager
                .post(path, emailPasswordRequest, SignInAndUpResponse.class);
        return signInUpResponse.getRecipeUserId();
    }
}
