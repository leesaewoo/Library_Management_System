package leejaewoo.server.rental.controller;

import leejaewoo.server.global.response.SingleResponse;
import leejaewoo.server.rental.dto.RentalPostDto;
import leejaewoo.server.rental.dto.RentalResponseDto;
import leejaewoo.server.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
