package leejaewoo.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import leejaewoo.server.book.controller.BookController;
import leejaewoo.server.book.dto.BookPostDto;
import leejaewoo.server.book.dto.BookResponseDto;
import leejaewoo.server.book.service.BookService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@AutoConfigureRestDocs
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @DisplayName("도서등록")
    @Test
    void postBook() throws Exception {
        //given
        List<String> categories = new ArrayList<>();
        categories.add("IT");
        categories.add("알고리즘");
        categories.add("공부");

        BookPostDto bookPostDto =
                BookPostDto.builder()
                        .name("알고리즘 문제해결")
                        .publisher("인사이트")
                        .publishedDate("2020.6.29")
                        .categories(categories)
                        .build();

        BookResponseDto bookResponseDto =
                BookResponseDto.builder()
                        .name("알고리즘 문제해결")
                        .publisher("인사이트")
                        .publishedDate(LocalDateTime.of(2020, 6, 29, 0, 0))
                        .category("IT, 알고리즘, 공부")
                        .build();
        given(bookService.createBook(any()))
                .willReturn(bookResponseDto);

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookPostDto)))
                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcRestDocumentation.document("books/postBook",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
