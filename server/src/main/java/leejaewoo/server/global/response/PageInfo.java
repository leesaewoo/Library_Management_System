package leejaewoo.server.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class PageInfo {

    private int page;
    private int size;
    private int totalPage;
    private int totalElements;

    public static PageInfo of(Page<?> page) {
        return new PageInfo(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                (int) page.getTotalElements()
        );
    }
}
