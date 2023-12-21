package leejaewoo.server.member.repository;

import leejaewoo.server.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
//    queryDSL 사용으로 인한 주석처리
//    Optional<Member> findByEmail(String Email);
}
