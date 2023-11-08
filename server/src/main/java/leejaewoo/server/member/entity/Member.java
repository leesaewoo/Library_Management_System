package leejaewoo.server.member.entity;

import leejaewoo.server.global.audit.Auditable;
import leejaewoo.server.rental.entity.Rental;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String id;

    private String password;

    private String name;

    private String phoneNumber;

    private String residentialAddress;

    private String email;

    @OneToMany(mappedBy = "member")
    private List<Rental> rentals;
}
