package leejaewoo.server.rental.service;

import leejaewoo.server.global.exception.rental.RentalNotFoundException;
import leejaewoo.server.global.exception.rental.RentalUnavailableException;
import leejaewoo.server.rental.dto.RentalPostDto;
import leejaewoo.server.rental.dto.RentalResponseDto;
import leejaewoo.server.rental.entity.Rental;
import leejaewoo.server.rental.entity.RentalStatus;
import leejaewoo.server.rental.mapper.RentalMapper;
import leejaewoo.server.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalMapper rentalMapper;

    private final RentalRepository rentalRepository;

    public RentalResponseDto createRental(RentalPostDto rentalPostDto) {

        Rental rental = rentalMapper.postDtoToRental(rentalPostDto);

        verifyAvailableRent(rentalPostDto.getBookId());

        rental = rentalRepository.save(rental);

        return rentalMapper.rentalToResponseDto(rental);
    }

    public RentalResponseDto returnBook(Long rentalId) {
        Rental rental = verifyExistRental(rentalId);

        rental.changeRentalState();

        return rentalMapper.rentalToResponseDto(rental);
    }

    public void verifyAvailableRent(Long bookId) {

        List<Rental> rentals = rentalRepository.findByBookBookId(bookId);

        if(!rentals.isEmpty()) {
            for (Rental rental : rentals) {
                if (rental.getStatus().equals(RentalStatus.ON_RENTAL)) {
                    throw new RentalUnavailableException();
                }
            }
        }
    }

    public Rental verifyExistRental(Long rentalId) {
        return rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
    }
}
