package leejaewoo.server.rental.repository;

import leejaewoo.server.rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
//    queryDSL 사용으로 인한 주석처리
//    List<Rental> findByBookBookId(Long bookId);
}
