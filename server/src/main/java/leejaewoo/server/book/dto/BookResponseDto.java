package leejaewoo.server.book.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class BookResponseDto {

    private Long bookId;

    private String name;

    private String publisher;

    private LocalDateTime publishedDate;

    private String category;
}
