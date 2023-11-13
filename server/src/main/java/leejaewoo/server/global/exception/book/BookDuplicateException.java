package leejaewoo.server.global.exception.book;

import org.springframework.http.HttpStatus;

public class BookDuplicateException extends BookException{

    private static final String MESSAGE = "이미 존재하는 도서입니다.";

    private static final String CODE = "BOOK-409";

    public BookDuplicateException() {
        super(CODE, HttpStatus.CONFLICT, MESSAGE);
    }
}
