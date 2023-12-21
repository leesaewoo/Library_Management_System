package leejaewoo.server.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class PageInfo {

    private int page;

    private int size;

    private int totalPage;

    private int totalElements;

    public PageInfo(int page, int size, int totalPage, int totalElements) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPage = totalPage;
    }
}
