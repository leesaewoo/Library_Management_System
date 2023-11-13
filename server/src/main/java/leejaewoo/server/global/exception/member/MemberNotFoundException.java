package leejaewoo.server.global.exception.member;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends MemberException {

    private static final String MESSAGE = "존재하지 않는 회원입니다.";

    private static final String CODE = "MEMBER-404";

    public MemberNotFoundException() {
        super(CODE, HttpStatus.NOT_FOUND, MESSAGE);
    }
}

