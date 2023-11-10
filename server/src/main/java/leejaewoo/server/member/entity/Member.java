package leejaewoo.server.member.entity;

import leejaewoo.server.global.audit.Auditable;
import leejaewoo.server.rental.entity.Rental;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String email;

    private String password;

    private String name;

    private String phoneNumber;

    private String residentialAddress;

    @OneToMany(mappedBy = "member")
    private List<Rental> rentals;
}
