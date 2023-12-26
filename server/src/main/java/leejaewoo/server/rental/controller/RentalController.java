package leejaewoo.server.rental.controller;

import leejaewoo.server.global.exception.rental.RentalNotFoundException;
import leejaewoo.server.global.exception.rental.RentalUnavailableException;
import leejaewoo.server.global.response.PageResponse;
import leejaewoo.server.global.response.SingleResponse;
import leejaewoo.server.rental.dto.RentalPostDto;
import leejaewoo.server.rental.dto.RentalResponseDto;
import leejaewoo.server.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    //도서 대출 신청
    @PostMapping
    public ResponseEntity postRental(@RequestBody @Valid RentalPostDto rentalPostDto) {

        RentalResponseDto result;

        try {
            result = rentalService.createRental(rentalPostDto);
        } catch (RentalUnavailableException rue) {
            return new ResponseEntity<>(rue.getMessage(), rue.getHttpStatus());
        }

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //도서 반납
    @PatchMapping("/return/{rental-id}")
    public ResponseEntity patchRental(@PathVariable("rental-id") @Positive Long rentalId) {

        RentalResponseDto result;

        try {
             result = rentalService.returnBook(rentalId);
        } catch (RentalNotFoundException rnfe) {
            return new ResponseEntity<>(rnfe.getMessage(), rnfe.getHttpStatus());
        }

        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    //대출 이력 조회
    @GetMapping("/{book-id}")
    public ResponseEntity<List<RentalResponseDto>> getRental(@PathVariable("book-id") Long bookId) {

        List<RentalResponseDto> result = rentalService.findRentals(bookId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //대출 이력 조회(페이징)
    @GetMapping("/page/{book-id}")
    public ResponseEntity<PageResponse<List<RentalResponseDto>>> getRental(@RequestParam("page_number") int pageNumber,
                                                                           @RequestParam("page_size") int pageSize,
                                                                           @PathVariable("book-id") Long bookId) {
        PageResponse<List<RentalResponseDto>> pageResponse =
                rentalService.findRentalsPagination(pageNumber, pageSize, bookId);

        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }
}
