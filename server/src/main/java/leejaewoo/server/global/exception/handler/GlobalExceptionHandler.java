package leejaewoo.server.global.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(BindException.class)
//    public ResponseEntity<ApiSingleResponse<List<ApiSingleResponse.ErrorResponse>>> handleBindException(
//            BindException e) {
//
//        return ResponseEntity.badRequest().body(ApiSingleResponse.fail(e));
//    }
}
