package leejaewoo.server.global.exception.book;

import leejaewoo.server.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public abstract class BookException extends BusinessException {

    public BookException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
