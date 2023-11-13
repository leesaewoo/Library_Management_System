package leejaewoo.server.global.exception.rental;

import org.springframework.http.HttpStatus;

public class RentalNotFoundException extends RentalException {

    private static final String MESSAGE = "올바르지 않은 대출 ID 입니다.";

    private static final String CODE = "RENTAL-404";

    public RentalNotFoundException() {
        super(CODE, HttpStatus.NOT_FOUND, MESSAGE);
    }
}
