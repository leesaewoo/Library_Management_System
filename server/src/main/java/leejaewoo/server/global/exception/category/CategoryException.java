package leejaewoo.server.global.exception.category;

import leejaewoo.server.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public abstract class CategoryException extends BusinessException {

    public CategoryException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
