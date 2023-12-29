package leejaewoo.server.member.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import leejaewoo.server.global.exception.member.MemberDuplicateException;
import leejaewoo.server.global.exception.member.MemberNotFoundException;
import leejaewoo.server.member.dto.MemberPostDto;
import leejaewoo.server.member.dto.MemberResponseDto;
import leejaewoo.server.member.entity.Member;
import leejaewoo.server.member.entity.QMember;
import leejaewoo.server.member.mapper.MemberMapper;
import leejaewoo.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    private final JPAQueryFactory jpaQueryFactory;

    public MemberResponseDto createMember(MemberPostDto memberPostDto) throws MemberDuplicateException {
        Member member = memberMapper.postDtoToMember(memberPostDto);

        verifyExistMember(member.getEmail());

        member = memberRepository.save(member);

        return memberMapper.memberToResponseDto(member);
    }

    public void verifyExistMember(String email) {
//        JPA 쿼리 메서드 -> queryDSL 변경
//        Optional<Member> findMember = memberRepository.findByEmail(email);
        Optional<Member> findMember = Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(QMember.member)
                        .where(QMember.member.email.eq(email))
                        .fetchOne()
        );

        if(findMember.isPresent()) {
            log.error("다음 멤버와 이메일 중복, memberId: " + findMember.get().getMemberId());
            throw new MemberDuplicateException();
        }
    }

    public Member findMemberByEmail(String email) {
        Optional<Member> findMember =
                Optional.ofNullable(
                        jpaQueryFactory
                            .selectFrom(QMember.member)
                            .where(QMember.member.email.eq(email))
                            .fetchOne()
                );

        if(findMember.isEmpty()) {
            log.error("멤버 조회 실패, email: " + email);
            throw new MemberNotFoundException();
        }

        Member result = findMember.get();
        log.info("멤버 조회 성공, memberId: " + result.getMemberId());

        return result;
    }
}
