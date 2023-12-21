package leejaewoo.server.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookPatchDto {

    private String name;

    private String publisher;

    //입력 양식: "yyyy-MM-dd"
    private String publishedDate;
}
