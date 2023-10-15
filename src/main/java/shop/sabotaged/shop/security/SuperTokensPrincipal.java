package shop.sabotaged.shop.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RequiredArgsConstructor
public class SuperTokensPrincipal implements Principal {

    private final String userId;
    private final String email;
    private final String sessionHandle;

    @Override
    public String getName() {
        return this.email;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getSessionHandle() {
        return this.sessionHandle;
    }
}
