package leejaewoo.server.global.exception;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException{

    private ExceptionCode exceptionCode;

    public GlobalException(ExceptionCode exceptionCode){
        super(exceptionCode.toString());
        this.exceptionCode = exceptionCode;
    }
}
