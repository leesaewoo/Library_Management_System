package leejaewoo.server.global.exception.category;

import org.springframework.http.HttpStatus;

public class CategoryDuplicateException extends CategoryException {

    private static final String MESSAGE = "이미 존재하는 카테고리 입니다.";

    private static final String CODE = "CATEGORY-409";

    public CategoryDuplicateException() {
        super(CODE, HttpStatus.CONFLICT, MESSAGE);
    }
}
