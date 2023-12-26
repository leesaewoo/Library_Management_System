package leejaewoo.server.mongotest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MongoService {

    private final MongoTemplate mongoTemplate;

    private final MongoOperations mongoOperations;

    public void mongoInsert(MongoRequestDto mongoRequestDto) {
        Mongo mongo = new Mongo(mongoRequestDto.getId(), mongoRequestDto.getContent1(), mongoRequestDto.getContent2(), mongoRequestDto.isContent3(), mongoRequestDto.getContent4());

        mongoTemplate.insert(mongo);
    }

    public void mongoInsert(Mongo2RequestDto mongo2RequestDto) {
        Mongo2 mongo2 = new Mongo2(mongo2RequestDto.getId(), mongo2RequestDto.getMongo2Content1(), mongo2RequestDto.getMongo2Content2());

        mongoTemplate.insert(mongo2);
    }

    public void mongoInsert(RedRecord redRecord) {
        mongoTemplate.insert(redRecord);
    }

    public void mongoUpdate(MongoRequestDto mongoRequestDto) {
        Mongo mongo = new Mongo(mongoRequestDto.getId(), mongoRequestDto.getContent1(), mongoRequestDto.getContent2(), mongoRequestDto.isContent3(), mongoRequestDto.getContent4());
    }

    public void mongoUpdate(Mongo2RequestDto mongo2RequestDto) {

    }

    public void mongoRemove() {

    }

    public void mongoFind() {

    }
}
