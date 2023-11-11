package leejaewoo.server.book.controller;

import leejaewoo.server.book.dto.BookPostDto;
import leejaewoo.server.book.dto.BookResponseDto;
import leejaewoo.server.book.service.BookService;
import leejaewoo.server.global.response.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<SingleResponse<BookResponseDto>> postBook(@RequestBody @Valid BookPostDto bookPostDto) {

        BookResponseDto result = bookService.createBook(bookPostDto);

        return ResponseEntity.ok(SingleResponse.create(result, "도서 등록 완료"));
    }
}
