package leejaewoo.server.rental.repository;

import leejaewoo.server.rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByBookBookId(Long bookId);
}
