package leejaewoo.server.book.repository;

import leejaewoo.server.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
//    queryDSL 사용으로 인한 주석처리
//    Optional<Book> findByName(String name);
}
