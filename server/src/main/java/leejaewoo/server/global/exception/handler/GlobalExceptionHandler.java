package leejaewoo.server.global.exception.handler;

import leejaewoo.server.global.exception.BusinessException;
import leejaewoo.server.global.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BusinessException e) {

        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage());

        String errorCodeString = e.getErrorCode();
        int errorCode = Integer.parseInt(errorCodeString.substring(errorCodeString.length() - 3));

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode));
    }
}
