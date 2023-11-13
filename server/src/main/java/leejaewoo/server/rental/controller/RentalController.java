package leejaewoo.server.rental.controller;

import leejaewoo.server.global.response.SingleResponse;
import leejaewoo.server.rental.dto.RentalPostDto;
import leejaewoo.server.rental.dto.RentalResponseDto;
import leejaewoo.server.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<SingleResponse<RentalResponseDto>> postRental(@RequestBody @Valid RentalPostDto rentalPostDto) {

        RentalResponseDto result = rentalService.createRental(rentalPostDto);

        return ResponseEntity.ok(SingleResponse.create(result, "도서 대출 성공"));
    }

    //도서 반납
    @PatchMapping("/return/{rental-id}")
    public ResponseEntity<SingleResponse<RentalResponseDto>> patchRental(@PathVariable("rental-id") @Positive Long rentalId) {

        RentalResponseDto result = rentalService.returnBook(rentalId);

        return ResponseEntity.ok(SingleResponse.accepted(result, "도서 반납 성공"));
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<SingleResponse<List<RentalResponseDto>>> getRental(@PathVariable("book-id") Long bookId) {

        List<RentalResponseDto> result = rentalService.findRentals(bookId);

        return ResponseEntity.ok(SingleResponse.ok(result, "도서 대출이력 확인 성공"));
    }
}
