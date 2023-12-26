package leejaewoo.server.book.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import leejaewoo.server.book.dto.BookPatchDto;
import leejaewoo.server.book.dto.BookPostDto;
import leejaewoo.server.book.dto.BookResponseDto;
import leejaewoo.server.book.entity.Book;
import leejaewoo.server.book.entity.QBook;
import leejaewoo.server.book.mapper.BookMapper;
import leejaewoo.server.book.repository.BookRepository;
import leejaewoo.server.bookcategory.entity.BookCategory;
import leejaewoo.server.bookcategory.service.BookCategoryService;
import leejaewoo.server.category.entity.Category;
import leejaewoo.server.category.service.CategoryService;
import leejaewoo.server.global.exception.book.BookDuplicateException;
import leejaewoo.server.global.exception.book.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    private final CategoryService categoryService;

    private final BookCategoryService bookCategoryService;

    private final JPAQueryFactory jpaQueryFactory;

    public BookResponseDto createBook(BookPostDto bookPostDto) {
        System.out.println(bookPostDto.getPublishedDate());
        Book book = bookMapper.postDtoToBook(bookPostDto);

        verifyExistBook(book.getName());

        book = bookRepository.save(book);
        Long bookId = book.getBookId();
        log.info("도서 등록, bookId: " + bookId);

        List<Category> categoryList = bookPostDto.getCategories().stream()
                .map(categoryService::createCategory)
                .collect(Collectors.toList());

        List<BookCategory> bookCategories = categoryList.stream()
                .map(o -> bookCategoryService.createBookCategory(bookId, o.getCategoryId()))
                .collect(Collectors.toList());

        book.setBookCategories(bookCategories);

        return bookMapper.BookToResponseDto(book, categoryList);
    }

    public BookResponseDto modifyBook(Long bookId, BookPatchDto bookPatchDto) {

        Book book = findBookByBookId(bookId);

        Optional.ofNullable(bookPatchDto.getName())
                .ifPresent(name -> {
                        book.setName(name);
                        log.info("도서 정보 수정, bookId: " + bookId + ", bookName: " + name);
                    }
                );
        Optional.ofNullable(bookPatchDto.getPublisher())
                .ifPresent(publisher -> {
                        book.setPublisher(publisher);
                        log.info("도서 정보 수정, bookId: " + bookId + ", publisher: " + publisher);
                    }
                );
        Optional.ofNullable(bookPatchDto.getPublishedDate())
                .ifPresent(date -> {
                        book.setPublishedDate(date);
                        log.info("도서 정보 수정, bookId: " + bookId + ", publishedDate: " + date);
                    }
                );

        List<Category> categoryList = book.getBookCategories().stream()
                .map(BookCategory::getCategory)
                .collect(Collectors.toList());

        return bookMapper.BookToResponseDto(book, categoryList);
    }

    public void verifyExistBook(String name) {
//        JPA 쿼리 메서드 -> queryDSL 변경
//        Optional<Book> findBook = bookRepository.findByName(name);
        Optional<Book> findBook =
                Optional.ofNullable(
                        jpaQueryFactory
                            .selectFrom(QBook.book)
                            .where(QBook.book.name.eq(name))
                            .fetchOne()
                );

        if(findBook.isPresent()) {
            log.error("도서명 중복, name: " + name);
            throw new BookDuplicateException();
        }
    }

    public Book findBookByBookId(Long bookId) {
//        JPA 쿼리 메서드 -> queryDSL 변경
//        Optional<Book> findBook = bookRepository.findById(bookId);
        Optional<Book> findBook =
                Optional.ofNullable(
                        jpaQueryFactory
                            .selectFrom(QBook.book)
                            .where(QBook.book.bookId.eq(bookId))
                            .fetchOne()
                );

        if(findBook.isEmpty()) {
            log.error("존재 하지 않는 도서, bookId: " + bookId);
            throw new BookNotFoundException();
        }

        return findBook.get();
    }
}
