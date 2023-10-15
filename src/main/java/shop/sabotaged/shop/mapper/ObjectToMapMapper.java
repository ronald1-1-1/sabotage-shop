package shop.sabotaged.shop.mapper;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class ObjectToMapMapper {

    public Map<String, Object> convertToMap(Object object) {
        Map<String, Object> hashMap = new HashMap<>();

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();
            String getterName = (field.getType() == Boolean.class) ? "is" : "get" +
                    field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);

            try {
                Method getterMethod = object.getClass().getMethod(getterName);
                Object value = getterMethod.invoke(object);
                hashMap.put(fieldName, value);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        return hashMap;
    }
}
