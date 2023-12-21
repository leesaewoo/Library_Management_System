package leejaewoo.server.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {

    private Long bookId;

    private String name;

    private String publisher;

    private LocalDateTime publishedDate;

    private String category;
}
