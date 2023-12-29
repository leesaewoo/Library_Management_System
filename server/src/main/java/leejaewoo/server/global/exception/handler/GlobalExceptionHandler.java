package leejaewoo.server.global.exception.handler;

import leejaewoo.server.global.exception.BusinessException;
import leejaewoo.server.global.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//모든 Controller 에서 발생하는 예외를 처리해준다
//메서드에 @ExceptionHandler(처리할예외.class)을 붙이면 해당 예외에 대한 처리를 할 수 있다
//다만 Controller 에서 직접 예외를 catch 한다면 해당 예외처리가 @RestControllerAdvice 보다 우선시 된다
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BusinessException e) {

        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage());

        String errorCodeString = e.getErrorCode();
        int errorCode = Integer.parseInt(errorCodeString.substring(errorCodeString.length() - 3));

        //log를 여기서 남기는것도 좋을 듯?

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode));
    }
}
