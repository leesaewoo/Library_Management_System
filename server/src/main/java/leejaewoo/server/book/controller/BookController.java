package leejaewoo.server.book.controller;

import leejaewoo.server.book.dto.BookPatchDto;
import leejaewoo.server.book.dto.BookPostDto;
import leejaewoo.server.book.dto.BookResponseDto;
import leejaewoo.server.book.service.BookService;
import leejaewoo.server.global.response.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{book-id}")
    public ResponseEntity<SingleResponse<BookResponseDto>> patchBook(@PathVariable(value = "book-id") Long bookId ,
                                                          @RequestBody @Valid BookPatchDto bookPatchDto) {
        BookResponseDto result = bookService.modifyBook(bookId, bookPatchDto);

        return ResponseEntity.ok(SingleResponse.accepted( result, "도서 수정 완료"));
    }
}
