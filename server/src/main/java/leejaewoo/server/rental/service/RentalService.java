package leejaewoo.server.rental.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import leejaewoo.server.book.entity.QBook;
import leejaewoo.server.book.service.BookService;
import leejaewoo.server.global.exception.rental.RentalNotFoundException;
import leejaewoo.server.global.exception.rental.RentalUnavailableException;
import leejaewoo.server.global.response.PageInfo;
import leejaewoo.server.global.response.PageResponse;
import leejaewoo.server.rental.dto.RentalPostDto;
import leejaewoo.server.rental.dto.RentalResponseDto;
import leejaewoo.server.rental.entity.QRental;
import leejaewoo.server.rental.entity.Rental;
import leejaewoo.server.rental.entity.RentalStatus;
import leejaewoo.server.rental.mapper.RentalMapper;
import leejaewoo.server.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RentalService {

    private final RentalMapper rentalMapper;

    private final RentalRepository rentalRepository;

    private final JPAQueryFactory jpaQueryFactory;

    public RentalResponseDto createRental(RentalPostDto rentalPostDto) throws RentalUnavailableException {

        Rental rental = rentalMapper.postDtoToRental(rentalPostDto);

        verifyAvailableRent(rentalPostDto.getBookId());

        rental = rentalRepository.save(rental);
        log.info("도서 대출 완료, rentalId: " + rental.getRentalId());

        return rentalMapper.rentalToResponseDto(rental);
    }

    public RentalResponseDto returnBook(Long rentalId) throws RentalNotFoundException{
        Rental rental = verifyExistRental(rentalId);

        rental.changeRentalState();
        log.info("도서 반납 완료, rentalId: " + rentalId);

        return rentalMapper.rentalToResponseDto(rental);
    }

    public List<RentalResponseDto> findRentals(Long bookId) {
//        JPA 쿼리 메서드 -> queryDSL 변경
//        List<Rental> rentals = rentalRepository.findByBookBookId(bookId);
        List<Rental> rentals = jpaQueryFactory
                .selectFrom(QRental.rental)
                .join(QRental.rental.book, QBook.book)
                .where(QRental.rental.book.bookId.eq(bookId))
                .orderBy(QRental.rental.rentalId.desc())
                .fetch();

        return rentalMapper.rentalsToResponseDtoList(rentals);
    }

    public PageResponse<List<RentalResponseDto>> findRentalsPagination(int pageNumber, int pageSize, Long bookId) {
        List<Rental> rentals = jpaQueryFactory
                .selectFrom(QRental.rental)
                .join(QRental.rental.book, QBook.book)
                .where(QRental.rental.book.bookId.eq(bookId))
                .offset(pageNumber - 1)
                .limit(pageSize)
                .orderBy(QRental.rental.rentalId.desc())
                .fetch();

        List<RentalResponseDto> responses = rentalMapper.rentalsToResponseDtoList(rentals);

        int totalSize = jpaQueryFactory
                .selectFrom(QRental.rental)
                .join(QRental.rental.book, QBook.book)
                .where(QRental.rental.book.bookId.eq(bookId))
                .fetch().size();

        PageInfo pageInfo = PageInfo.builder()
                .page(pageNumber)
                .size(pageSize)
                .totalElements(totalSize)
                .totalPage(totalSize / pageSize)
                .build();

        PageResponse<List<RentalResponseDto>> pageResponse = new PageResponse<>(responses, pageInfo);
        log.info("도서대출이력 조회 성공(Pagination), bookId: " + bookId);

        return pageResponse;
    }

    public void verifyAvailableRent(Long bookId) {
//        JPA 쿼리 메서드 -> queryDSL 변경
//        List<Rental> rentals = rentalRepository.findByBookBookId(bookId);

        List<Rental> rentals = jpaQueryFactory
                .selectFrom(QRental.rental)
                .join(QRental.rental.book, QBook.book)
                .where(QRental.rental.book.bookId.eq(bookId))
                .fetch();

        if(rentals.isEmpty()) {
            return;
        }

        //
        for (Rental rental : rentals) {
            if (rental.getStatus().equals(RentalStatus.ON_RENTAL)) {
                log.error("대출중인 도서에 대출 신청, rentalId: " + rental.getRentalId());
                throw new RentalUnavailableException();
            }
        }
    }

    public Rental verifyExistRental(Long rentalId) {
//        JPA 쿼리 메서드 -> queryDSL 변경
//        Rental findRental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);

        Optional<Rental> findRental =
                Optional.ofNullable(
                        jpaQueryFactory
                            .selectFrom(QRental.rental)
                            .where(QRental.rental.rentalId.eq(rentalId))
                            .fetchOne()
                );

        if(findRental.isEmpty()) {
            log.error("존재하지 않는 대출, rentalId: " + rentalId);
            throw new RentalNotFoundException();
        }

        return findRental.get();
    }
}
