package leejaewoo.server.mongotest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mongo2RequestDto {

    private String id;

    private int mongo2Content1;

    private String mongo2Content2;
}
