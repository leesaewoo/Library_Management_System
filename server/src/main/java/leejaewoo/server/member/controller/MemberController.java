package leejaewoo.server.member.controller;

import leejaewoo.server.global.exception.member.MemberDuplicateException;
import leejaewoo.server.global.response.SingleResponse;
import leejaewoo.server.member.dto.MemberPostDto;
import leejaewoo.server.member.dto.MemberResponseDto;
import leejaewoo.server.member.entity.Member;
import leejaewoo.server.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping
    public ResponseEntity postMember(@RequestBody @Valid MemberPostDto memberPostDto) {

        MemberResponseDto result;

        try {
             result = memberService.createMember(memberPostDto);
        } catch (MemberDuplicateException mde) {
            return new ResponseEntity<>("이미 가입된 이메일 주소 입니다.", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
