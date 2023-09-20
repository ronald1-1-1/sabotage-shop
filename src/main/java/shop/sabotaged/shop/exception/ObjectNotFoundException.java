package shop.sabotaged.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String objectName, Object id) {
        this(String.format("%s with id %s not found", objectName, id.toString()));
    }

}
