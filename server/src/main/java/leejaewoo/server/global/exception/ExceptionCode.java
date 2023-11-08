package leejaewoo.server.global.exception;

public enum ExceptionCode {
    MEMBER_NOT_FOUND("MEMBER_NOT_FOUND", 404),
    BOOK_NOT_FOUND("BOOK_NOT_FOUND", 404),
    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", 404),
    RENTAL_NOT_FOUND("RENTAL_NOT_FOUND", 404),
    MEMBER_EXISTS("MEMBER_EXISTS", 403),
    BOOK_EXISTS("BOOK_EXISTS", 403),
    CATEGORY_EXISTS("CATEGORY_EXISTS", 403),
    RENTAL_EXISTS("RENTAL_EXISTS", 403);

    private String message;

    private int statusNum;

    ExceptionCode(String message, int statusNum) {
        this.message = message;
        this.statusNum = statusNum;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusNum() {
        return statusNum;
    }
}
