package leejaewoo.server.rental.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RentalPostDto {

    private Long bookId;

    private String email;

    private int period;
}
