package leejaewoo.server.global.exception.rental;

import org.springframework.http.HttpStatus;

public class RentalUnavailableException extends RentalException {

    public static final String MESSAGE = "대여가 불가능한 도서입니다.";

    public static final String CODE = "RENTAL-409";

    public RentalUnavailableException() {
        super(CODE, HttpStatus.CONFLICT, MESSAGE);
    }
}
