package shop.sabotaged.shop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import shop.sabotaged.shop.exception.auth.SuperTokensRequestException;
import shop.sabotaged.shop.jwt.JwtDecoder;
import shop.sabotaged.shop.security.supertokens.SuperTokensRoleManager;
import shop.sabotaged.shop.security.supertokens.SuperTokensSessionManager;

import java.util.List;

@Component
@RequiredArgsConstructor
class AuthenticationManagerImpl implements AuthenticationManager {

    private final SuperTokensSessionManager sessionManager;
    private final SuperTokensRoleManager roleManager;
    private final JwtDecoder jwtDecoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreAuthenticatedAuthenticationToken authenticationToken =
                (PreAuthenticatedAuthenticationToken) authentication;
        String accessToken = (String) authenticationToken.getCredentials();
        if (accessToken == null) throw new BadCredentialsException("Access token not found");
        String userId;
        try {
            userId = sessionManager.verifySession(accessToken);
        } catch (SuperTokensRequestException exception) {
            if (exception.getMessage().equals(SuperTokensRequestException.TOKEN_EXPIRED_STATUS)) {
                throw new CredentialsExpiredException("Token is expired");
            }
            throw new BadCredentialsException("Bad credentials");
        }
        List<String> roles;
        try {
            roles = roleManager.getUserRoles(userId);
        } catch (SuperTokensRequestException exception) {
            throw new BadCredentialsException("Bad credentials");
        }
        String email = jwtDecoder.getEmail(accessToken);
        String sessionHandle = jwtDecoder.getSessionHandle(accessToken);
        SuperTokensPrincipal superTokensPrincipal = new SuperTokensPrincipal(userId, email, sessionHandle);
        return new SuperTokensAuthentication(superTokensPrincipal, accessToken, true, roles);
    }
}
