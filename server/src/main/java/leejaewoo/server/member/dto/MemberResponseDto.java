package leejaewoo.server.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private Long memberId;

    private String email;

    private String name;

    private String phoneNumber;

    private String residentialAddress;
}
