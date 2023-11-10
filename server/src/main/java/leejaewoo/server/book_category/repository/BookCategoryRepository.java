package leejaewoo.server.book_category.repository;

import leejaewoo.server.book_category.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
}
