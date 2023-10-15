package shop.sabotaged.shop.security.filer;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SuperTokensTokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationConverter authenticationConverter;
    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication preAuthentication = authenticationConverter.convert(request);
        if (preAuthentication != null) {
            try {
                Authentication authentication = authenticationManager.authenticate(preAuthentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (AuthenticationException authenticationException) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
