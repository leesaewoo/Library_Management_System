package leejaewoo.server.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookPostDto {

    private String name;

    private String publisher;

    //입력 양식: "yyyy-MM-dd"
    private String publishedDate;

    private List<String> categories;
}
