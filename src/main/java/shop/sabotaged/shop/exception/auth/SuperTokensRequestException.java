package shop.sabotaged.shop.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class SuperTokensRequestException extends RuntimeException {

    public static final String TOKEN_EXPIRED_STATUS = "TRY_REFRESH_TOKEN";

    public SuperTokensRequestException(String message) {
        super(message);
    }
}
