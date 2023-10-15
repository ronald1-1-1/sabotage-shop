package shop.sabotaged.shop.security.supertokens.model.type;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class User {
    private String id;
    private boolean isPrimaryUser;
    private List<String> tenantIds;
    private long timeJoined;
    private List<String> emails;
    private List<String> phoneNumbers;
    private List<String> thirdParty;
    private List<LoginMethod> loginMethods;
}
