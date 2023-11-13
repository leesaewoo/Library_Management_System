package leejaewoo.server.global.exception.member;

import leejaewoo.server.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public abstract class MemberException extends BusinessException {

    public MemberException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
