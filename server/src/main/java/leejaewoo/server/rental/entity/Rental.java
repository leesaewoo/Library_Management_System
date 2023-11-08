package leejaewoo.server.rental.entity;

import leejaewoo.server.book.entity.Book;
import leejaewoo.server.global.audit.Auditable;
import leejaewoo.server.member.entity.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Rental extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;

    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    private int period;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}