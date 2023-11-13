package leejaewoo.server.global.exception.rental;

import leejaewoo.server.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class RentalException extends BusinessException {

    public RentalException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
