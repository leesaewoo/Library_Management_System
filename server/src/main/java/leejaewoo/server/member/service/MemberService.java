package leejaewoo.server.member.service;

import leejaewoo.server.global.exception.member.MemberDuplicateException;
import leejaewoo.server.member.dto.MemberPostDto;
import leejaewoo.server.member.dto.MemberResponseDto;
import leejaewoo.server.member.entity.Member;
import leejaewoo.server.member.mapper.MemberMapper;
import leejaewoo.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public final MemberMapper memberMapper;

    public MemberResponseDto createMember(MemberPostDto memberPostDto) {
        Member member = memberMapper.postDtoToMember(memberPostDto);

        verifyExistMember(member.getEmail());

        member = memberRepository.save(member);

        return memberMapper.memberToResponseDto(member);
    }

    public void verifyExistMember(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if(findMember.isPresent()) {
            throw new MemberDuplicateException();
        }
    }
}
