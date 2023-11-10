package leejaewoo.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import leejaewoo.server.member.controller.MemberController;
import leejaewoo.server.member.dto.MemberPostDto;
import leejaewoo.server.member.dto.MemberResponseDto;
import leejaewoo.server.member.repository.MemberRepository;
import leejaewoo.server.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@AutoConfigureRestDocs
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @DisplayName("회원가입")
    @Test
    void postMember() throws Exception {
        //given
        MemberPostDto memberPostDto =
                MemberPostDto.builder()
                        .email("responseEmail@gmail.com")
                        .password("@abc123456")
                        .name("leejaewoo")
                        .phoneNumber("01011112222")
                        .residentialAddress("대전 서구 만년동 abc아파트 1동 1호")
                        .build();

        MemberResponseDto memberResponseDto =
                MemberResponseDto.builder()
                        .memberId(1L)
                        .email("responseEmail@gmail.com")
                        .name("leejaewoo")
                        .phoneNumber("01011112222")
                        .residentialAddress("대전 서구 만년동 abc아파트 1동 1호")
                        .build();

        given(memberService.createMember(any(MemberPostDto.class)))
                .willReturn(memberResponseDto);

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberPostDto)))
                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcRestDocumentation.document("members/postMember",
                        Preprocessors.preprocessRequest(prettyPrint()),
                        Preprocessors.preprocessResponse(prettyPrint())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
