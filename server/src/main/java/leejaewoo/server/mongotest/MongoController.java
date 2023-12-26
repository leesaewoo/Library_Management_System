package leejaewoo.server.mongotest;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mongotest")
@RequiredArgsConstructor
public class MongoController {

    private final MongoService mongoService;

    @PostMapping("/insert-mongo")
    public void mongoTest01(
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            @RequestBody MongoRequestDto requestDto) {
        mongoService.mongoInsert(requestDto);
    }

    @PostMapping("/insert-mongo2")
    public void mongoTest03(@RequestBody Mongo2RequestDto requestDto) {
        mongoService.mongoInsert(requestDto);
    }

    @PostMapping("/insert-red")
    public void mongoTestRedRecord(
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            @RequestBody RedRecord redRecord) {
        mongoService.mongoInsert(redRecord);
    }

    @PatchMapping("/update-mongo")
    public void mongoTest04(@RequestBody MongoRequestDto requestDto) {

    }

    @PatchMapping("/update-mongo2")
    public void mongoTest05(@RequestBody Mongo2RequestDto requestDto) {

    }

    @DeleteMapping("/remove")
    public void mongoTest02() {
        mongoService.mongoRemove();
    }
}
