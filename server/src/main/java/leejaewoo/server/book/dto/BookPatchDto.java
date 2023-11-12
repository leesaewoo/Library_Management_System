package leejaewoo.server.book.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookPatchDto {

    private String name;

    private String publisher;

    //입력 양식: "yyyy-MM-dd"
    private String publishedDate;
}
