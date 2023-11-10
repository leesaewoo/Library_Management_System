package leejaewoo.server.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class SingleResponse<T> {

    private T data;

    private int code;

    private String status;

    private String message;

    public static <T> SingleResponse<T> ok(T data, String message) {
        return SingleResponse.of(data,HttpStatus.OK, message);
    }

    public static <T> SingleResponse<T> of(T data, HttpStatus httpStatus) {
        return SingleResponse.of(data, httpStatus, httpStatus.getReasonPhrase());
    }

    public static <T> SingleResponse<T> of(T data, HttpStatus httpStatus, String message) {
        return new SingleResponse<>(
                data,
                httpStatus.value(),
                httpStatus.name(),
                message
                );
    }
}
