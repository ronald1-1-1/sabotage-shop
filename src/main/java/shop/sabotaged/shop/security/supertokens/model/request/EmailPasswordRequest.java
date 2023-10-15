package shop.sabotaged.shop.security.supertokens.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailPasswordRequest {
    private String email;
    private String password;
}
