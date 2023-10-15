package shop.sabotaged.shop.security.supertokens;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import shop.sabotaged.shop.exception.auth.SuperTokensRequestException;
import shop.sabotaged.shop.mapper.ObjectToMapMapper;
import shop.sabotaged.shop.security.supertokens.model.response.StatusResponse;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SuperTokensRequestManager {

    private static final String GET_STATUS_METHOD = "getStatus";

    @Value("${supertokens.host}")
    private String superTokensHost;

    private final RestTemplate restTemplate;
    private final ObjectToMapMapper objectToMapMapper;

    public <QUERY, RESPONSE> RESPONSE get(String path, QUERY query, Class<RESPONSE> responseClass) {
        String url = superTokensHost + path + uriVariablesString(query);
        RESPONSE response = restTemplate.getForObject(url, responseClass);
        checkStatus(response);
        return response;
    }

    public <REQUEST, RESPONSE> RESPONSE post(String path, REQUEST request, Class<RESPONSE> responseClass) {
        String url = superTokensHost + path;
        RESPONSE response = restTemplate.postForObject(url, request, responseClass);
        checkStatus(response);
        return response;
    }
    public <REQUEST> void put(String path, REQUEST request) {
        String url = superTokensHost + path;
        restTemplate.put(url, request);
    }

    private <QUERY> String uriVariablesString(QUERY query) {
        Map<String, Object> map = objectToMapMapper.convertToMap(query);
        StringBuilder stringBuilder = new StringBuilder("?");
        map.forEach((name, value) -> {
            stringBuilder.append(name);
            stringBuilder.append("=");
            stringBuilder.append(value);
            stringBuilder.append("&");
        });
        return stringBuilder.toString();
    }

    private <RESPONSE> void checkStatus(RESPONSE response) {
        String status;
        try {
            StatusResponse statusResponse = (StatusResponse) response;
            status = statusResponse.getStatus();
        } catch (Exception ignored) {
            return;
        }
        if (!status.equalsIgnoreCase("OK")) throw new SuperTokensRequestException(status);
    }

}
