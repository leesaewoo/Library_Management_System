package leejaewoo.server.global.exception.book;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends BookException {

    private static final String MESSAGE = "존재하지 않는 도서입니다.";

    private static final String CODE = "BOOK-404";

    public BookNotFoundException() {
        super(CODE, HttpStatus.NOT_FOUND, MESSAGE);
    }
}
