package leejaewoo.server.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity handleGlobalException(GlobalException globalException) {
        String errorMessage = globalException.getExceptionCode().getMessage();
        int errorStatusCode = globalException.getExceptionCode().getStatusNum();

        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(errorStatusCode));
    }
}
