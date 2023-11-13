package leejaewoo.server.bookcategory.service;

import leejaewoo.server.book.entity.Book;
import leejaewoo.server.bookcategory.entity.BookCategory;
import leejaewoo.server.bookcategory.repository.BookCategoryRepository;
import leejaewoo.server.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;

    public BookCategory createBookCategory(Long bookId, Long categoryId) {
        BookCategory bookCategory =
                BookCategory.builder()
                        .book(Book.builder()
                                .bookId(bookId)
                                .build())
                        .category(Category.builder()
                                .categoryId(categoryId)
                                .build())
                        .build();

        return bookCategoryRepository.save(bookCategory);
    }
}
