package leejaewoo.server.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@AllArgsConstructor
public class MemberPostDto {

    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,16}$", message = "하나 이상의 영문, 숫자, 특수문자가 조합되어야 합니다.")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9가-힣]+$", message = "이름엔 영문, 숫자, 한글만 입력 가능합니다.")
    private String name;

    @Pattern(regexp = "^(01[016789])-(\\d{3,4})-(\\d{4})$|^(01[016789]\\d{7,8})$", message = "올바른 핸드폰 번호가 아닙니다.")
    private String phoneNumber;

    private String residentialAddress;
}
