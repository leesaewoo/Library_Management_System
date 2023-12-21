package leejaewoo.server.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalPostDto {

    private Long bookId;

    private String email;

    private int period;
}
