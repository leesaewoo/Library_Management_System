package leejaewoo.server.bookcategory.repository;

import leejaewoo.server.bookcategory.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
}
