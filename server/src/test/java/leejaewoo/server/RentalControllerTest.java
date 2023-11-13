package leejaewoo.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import leejaewoo.server.rental.controller.RentalController;
import leejaewoo.server.rental.dto.RentalPostDto;
import leejaewoo.server.rental.dto.RentalResponseDto;
import leejaewoo.server.rental.entity.RentalStatus;
import leejaewoo.server.rental.service.RentalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@AutoConfigureRestDocs
@WebMvcTest(RentalController.class)
public class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RentalService rentalService;

    @DisplayName("도서대출신청")
    @Test
    void postRental() throws Exception {
        //given
        RentalPostDto rentalPostDto = RentalPostDto.builder()
                .bookId(1L)
                .email("responseEmail@gmail.com")
                .period(7)
                .build();
        RentalResponseDto rentalResponseDto = RentalResponseDto.builder()
                .rentalId(1L)
                .bookName("알고리즘 문제해결")
                .memberName("leejaewoo")
                .period(7)
                .rentalStatus(RentalStatus.ON_RENTAL.getName())
                .build();

        given(rentalService.createRental(any()))
                .willReturn(rentalResponseDto);

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/rentals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rentalPostDto)))
                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcRestDocumentation.document("rentals/postRental",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
