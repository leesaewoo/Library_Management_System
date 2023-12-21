package leejaewoo.server.mongotest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Document(collection = "test")
@AllArgsConstructor
@NoArgsConstructor
public class Mongo {

    @Id
    private String id;

    private String content1;

    private int content2;

    private boolean content3;

    private LocalDateTime content4;
}
