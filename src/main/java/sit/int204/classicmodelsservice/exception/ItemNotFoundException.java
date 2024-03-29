package sit.int204.classicmodelsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String messege) {
        super(messege);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
