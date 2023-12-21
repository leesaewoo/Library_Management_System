package leejaewoo.server.member.controller;

import leejaewoo.server.global.response.SingleResponse;
import leejaewoo.server.member.dto.MemberPostDto;
import leejaewoo.server.member.dto.MemberResponseDto;
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

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping
    public ResponseEntity<MemberResponseDto> postMember(@RequestBody @Valid MemberPostDto memberPostDto) {

        MemberResponseDto result = memberService.createMember(memberPostDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
