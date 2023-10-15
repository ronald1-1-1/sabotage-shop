package shop.sabotaged.shop.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationConverterImpl implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) return null;

        String[] parts = authorization.split("\\s+");
        if (parts.length < 2) return null;

        return new PreAuthenticatedAuthenticationToken(
                null,
                parts[1]);
    }
}
