package shop.sabotaged.shop.jwt;

import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtDecoder {

    public String getEmail(String token) {
        return (String) getValue(token, "email");
    }

    public String getSessionHandle(String token) {
        return (String) getValue(token, "sessionHandle");
    }

    public Object getValue(String token, String key) {
        String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];

        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        Map map = new Gson().fromJson(body, Map.class);
        return map.get(key);
    }

}
