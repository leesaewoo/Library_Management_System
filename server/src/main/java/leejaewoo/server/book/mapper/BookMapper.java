package leejaewoo.server.book.mapper;

import leejaewoo.server.book.dto.BookPostDto;
import leejaewoo.server.book.dto.BookResponseDto;
import leejaewoo.server.book.entity.Book;
import leejaewoo.server.category.entity.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public Book postDtoToBook(BookPostDto bookPostDto) {
        if(bookPostDto == null) {
            return null;
        }
        else {
            String[] date = bookPostDto.getPublishedDate().split("-");
            LocalDateTime publishedDate = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), 0, 0);

            return Book.builder()
                    .name(bookPostDto.getName())
                    .publisher(bookPostDto.getPublisher())
                    .publishedDate(publishedDate)
                    .build();
        }
    }

    public BookResponseDto BookToResponseDto(Book book, List<Category> categories) {
        if(book == null) {
            return null;
        }
        else {
            List<String> categoryNames = categories.stream().map(Category::getName).collect(Collectors.toList());

            StringJoiner stringJoiner = new StringJoiner(", ");

            String joinedCategoryName;

            if(categoryNames.size() == 1) {
                joinedCategoryName = categoryNames.get(0);
            }
            else {
                for(String categoryName : categoryNames) {
                    stringJoiner.add(categoryName);
                }
                joinedCategoryName = stringJoiner.toString();
            }

            return BookResponseDto.builder()
                    .bookId(book.getBookId())
                    .name(book.getName())
                    .publisher(book.getPublisher())
                    .publishedDate(book.getPublishedDate())
                    .category(joinedCategoryName)
                    .build();
        }
    }
}
