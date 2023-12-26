package leejaewoo.server.bookcategory.repository;

import com.mongodb.client.MongoDatabase;
import leejaewoo.server.bookcategory.entity.BookCategory;
import leejaewoo.server.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
}
