package leejaewoo.server.rental.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RentalResponseDto {

    private Long rentalId;

    private String bookName;

    private String memberName;

    private int period;

    private String rentalStatus;
}
