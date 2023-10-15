package shop.sabotaged.shop.security.supertokens;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shop.sabotaged.shop.dto.auth.TokensDto;
import shop.sabotaged.shop.security.supertokens.model.request.session.CreateSessionRequest;
import shop.sabotaged.shop.security.supertokens.model.request.session.RefreshSessionRequest;
import shop.sabotaged.shop.security.supertokens.model.request.session.RemoveSessionRequest;
import shop.sabotaged.shop.security.supertokens.model.request.session.VerifySessionRequest;
import shop.sabotaged.shop.security.supertokens.model.response.StatusResponse;
import shop.sabotaged.shop.security.supertokens.model.response.session.CreateAndRefreshSessionResponse;
import shop.sabotaged.shop.security.supertokens.model.response.session.VerifySessionResponse;
import shop.sabotaged.shop.security.supertokens.model.type.Email;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SuperTokensSessionManager {

    private static final String CREATE_SESSION_PATH = "/public/recipe/session";
    private static final String VERIFY_SESSION_PATH = "/recipe/session/verify";
    private static final String REFRESH_SESSION_PATH = "/recipe/session/refresh";
    private static final String REMOVE_SESSION_PATH = "/public/recipe/session/remove";

    private final SuperTokensRequestManager superTokensRequestManager;



    public TokensDto createSession(String userId, String email) {
        CreateSessionRequest createSessionRequest = CreateSessionRequest.builder()
                .userId(userId)
                .userDataInJWT(new Email(email))
                .userDataInDatabase(new Email(email))
                .enableAntiCsrf(false)
                .build();

        CreateAndRefreshSessionResponse response = superTokensRequestManager
                .post(CREATE_SESSION_PATH, createSessionRequest, CreateAndRefreshSessionResponse.class);
        return new TokensDto(
                response.getAccessToken().getToken(),
                response.getRefreshToken().getToken());
    }

    public String verifySession(String accessToken) {
        VerifySessionRequest verifySessionRequest = VerifySessionRequest.builder()
                .accessToken(accessToken)
                .enableAntiCsrf(false)
                .doAntiCsrfCheck(false)
                .checkDatabase(false)
                .antiCsrfToken("")
                .build();
        VerifySessionResponse response = superTokensRequestManager
                .post(VERIFY_SESSION_PATH, verifySessionRequest, VerifySessionResponse.class);
        return response.getSession().getRecipeUserId();
    }

    public void removeSession(String sessionHandle) {
        RemoveSessionRequest removeSessionRequest = new RemoveSessionRequest(List.of(sessionHandle));
        superTokensRequestManager.post(REMOVE_SESSION_PATH, removeSessionRequest, StatusResponse.class);
    }

    public TokensDto refreshSession(String refreshToken) {
        RefreshSessionRequest refreshSessionRequest = RefreshSessionRequest.builder()
                .refreshToken(refreshToken)
                .enableAntiCsrf(false)
                .antiCsrfToken("")
                .build();

        CreateAndRefreshSessionResponse response = superTokensRequestManager
                .post(REFRESH_SESSION_PATH, refreshSessionRequest, CreateAndRefreshSessionResponse.class);
        return new TokensDto(
                response.getAccessToken().getToken(),
                response.getRefreshToken().getToken());
    }

}
