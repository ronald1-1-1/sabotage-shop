package shop.sabotaged.shop.security.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.sabotaged.shop.dto.auth.EmailPasswordDto;
import shop.sabotaged.shop.dto.auth.RefreshTokenDto;
import shop.sabotaged.shop.dto.auth.TokensDto;
import shop.sabotaged.shop.security.SuperTokensPrincipal;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signin")
    @PreAuthorize("!isAuthenticated()")
    public TokensDto signIn(@Valid @RequestBody EmailPasswordDto emailPasswordDto) {
        return authService.signIn(emailPasswordDto.getEmail(), emailPasswordDto.getPassword());
    }

    @PostMapping("/auth/signout")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity signOut(@AuthenticationPrincipal SuperTokensPrincipal principal) {
        try {
            authService.signOut(principal.getSessionHandle());
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/auth/signup")
    @PreAuthorize("!isAuthenticated()")
    public TokensDto signUp(@Valid @RequestBody EmailPasswordDto emailPasswordDto) {
        return authService.signUp(emailPasswordDto.getEmail(), emailPasswordDto.getPassword());
    }

    @PostMapping("/auth/refresh")
    @PreAuthorize("permitAll()")
    public TokensDto refreshToken(@Valid @RequestBody RefreshTokenDto refreshTokenDto) {
        return authService.refreshToken(refreshTokenDto.getRefreshToken());
    }

}
