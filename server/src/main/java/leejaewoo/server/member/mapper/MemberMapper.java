package leejaewoo.server.member.mapper;

import leejaewoo.server.member.dto.MemberPostDto;
import leejaewoo.server.member.dto.MemberResponseDto;
import leejaewoo.server.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member postDtoToMember(MemberPostDto memberPostDto) {
        if(memberPostDto == null) {
            return null;
        }
        else {
            return Member.builder()
                    .email(memberPostDto.getEmail())
                    .password(memberPostDto.getPassword())
                    .name(memberPostDto.getName())
                    .phoneNumber(memberPostDto.getPhoneNumber())
                    .residentialAddress(memberPostDto.getResidentialAddress())
                    .build();
        }
    }

    public MemberResponseDto memberToResponseDto(Member member) {
        if(member == null) {
            return null;
        }
        else {
            return MemberResponseDto.builder()
                    .memberId(member.getMemberId())
                    .email(member.getEmail())
                    .name(member.getName())
                    .phoneNumber(member.getPhoneNumber())
                    .residentialAddress(member.getResidentialAddress())
                    .build();
        }
    }
}
