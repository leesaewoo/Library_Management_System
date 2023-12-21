package leejaewoo.server.rental.mapper;

import leejaewoo.server.book.entity.Book;
import leejaewoo.server.book.repository.BookRepository;
import leejaewoo.server.book.service.BookService;
import leejaewoo.server.global.exception.member.MemberNotFoundException;
import leejaewoo.server.member.entity.Member;
import leejaewoo.server.member.repository.MemberRepository;
import leejaewoo.server.member.service.MemberService;
import leejaewoo.server.rental.dto.RentalPostDto;
import leejaewoo.server.rental.dto.RentalResponseDto;
import leejaewoo.server.rental.entity.Rental;
import leejaewoo.server.rental.entity.RentalStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RentalMapper {

    private final MemberService memberService;

    private final BookService bookService;

    public Rental postDtoToRental(RentalPostDto rentalPostDto) {
        if(rentalPostDto == null) {
            return null;
        }
        else {
            return Rental.builder()
                    .book(Book.builder()
                            .bookId(rentalPostDto.getBookId())
                            .build())
                    .member(Member.builder()
                            .memberId(memberService.findMemberByEmail(rentalPostDto.getEmail()).getMemberId())
                            .email(rentalPostDto.getEmail())
                            .build())
                    .period(rentalPostDto.getPeriod())
                    .status(RentalStatus.ON_RENTAL)
                    .build();
        }
    }

    public RentalResponseDto rentalToResponseDto(Rental rental) {
        if(rental == null) {
            return null;
        }
        else {
            return RentalResponseDto.builder()
                    .rentalId(rental.getRentalId())
                    .bookName(bookService.findBookByBookId(rental.getBook().getBookId()).getName())
                    .memberName(memberService.findMemberByEmail(rental.getMember().getEmail()).getName())
                    .period(rental.getPeriod())
                    .rentalStatus(rental.getStatus().getName())
                    .build();
        }
    }

    public List<RentalResponseDto> rentalsToResponseDtoList(List<Rental> rentals) {
        if(rentals == null) {
            return null;
        }
        else {
            return rentals.stream().map(this::rentalToResponseDto).collect(Collectors.toList());
        }
    }
}
