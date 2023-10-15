package shop.sabotaged.shop.security.supertokens.model.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private String token;
    private long expiry;
    private long createdTime;
}
