package leejaewoo.server.member.entity;

import leejaewoo.server.global.audit.Auditable;
import leejaewoo.server.rental.entity.Rental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    private final List<Rental> rentals = new ArrayList<>();
}
