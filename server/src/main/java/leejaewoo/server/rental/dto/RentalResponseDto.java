package leejaewoo.server.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalResponseDto {

    private Long rentalId;

    private String bookName;

    private String memberName;

    private int period;

    private String rentalStatus;
}
