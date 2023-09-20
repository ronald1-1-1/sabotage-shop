package shop.sabotaged.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughVariantAmountException extends RuntimeException {

    public NotEnoughVariantAmountException(String message) {
        super(message);
    }

}
