package leejaewoo.server.mongotest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MongoRequestDto {

    private String id;

    private String content1;

    private int content2;

    private boolean content3;

    private LocalDateTime content4;
}
