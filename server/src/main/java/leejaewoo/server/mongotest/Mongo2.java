package leejaewoo.server.mongotest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "test")
@AllArgsConstructor
@NoArgsConstructor
public class Mongo2 {

    @Id
    private String id;

    private int mongo2Content1;

    private String mongo2Content2;
}
