package shop.sabotaged.shop.security.supertokens.model.request.session;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RemoveSessionRequest {
    private List<String> sessionHandles;
}
