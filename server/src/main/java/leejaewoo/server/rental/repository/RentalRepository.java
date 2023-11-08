package leejaewoo.server.rental.repository;

import leejaewoo.server.rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Long, Rental> {
}
