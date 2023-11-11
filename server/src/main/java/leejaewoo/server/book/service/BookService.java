package leejaewoo.server.book.service;

import leejaewoo.server.book.dto.BookPostDto;
import leejaewoo.server.book.dto.BookResponseDto;
import leejaewoo.server.book.entity.Book;
import leejaewoo.server.book.mapper.BookMapper;
import leejaewoo.server.book.repository.BookRepository;
import leejaewoo.server.bookcategory.entity.BookCategory;
import leejaewoo.server.bookcategory.service.BookCategoryService;
import leejaewoo.server.category.entity.Category;
import leejaewoo.server.category.service.CategoryService;
import leejaewoo.server.global.exception.book.BookDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    private final CategoryService categoryService;

    private final BookCategoryService bookCategoryService;

    public BookResponseDto createBook(BookPostDto bookPostDto) {
        System.out.println(bookPostDto.getPublishedDate());
        Book book = bookMapper.postDtoToBook(bookPostDto);

        verifyExistBook(book.getName());

        book = bookRepository.save(book);

        List<Category> categoryList = bookPostDto.getCategories().stream().map(categoryService::createCategory).collect(Collectors.toList());
        Long bookId = book.getBookId();
        List<BookCategory> bookCategories = categoryList.stream().map(o -> bookCategoryService.createBookCategory(bookId, o.getCategoryId())).collect(Collectors.toList());
        book.setBookCategories(bookCategories);

        return bookMapper.BookToResponseDto(book, categoryList);
    }

    public void verifyExistBook(String name) {

        Optional<Book> findBook = bookRepository.findByName(name);

        if(findBook.isPresent()) {
            throw new BookDuplicateException();
        }
    }
}
