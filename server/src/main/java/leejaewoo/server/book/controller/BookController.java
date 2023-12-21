package leejaewoo.server.book.controller;

import leejaewoo.server.book.dto.BookPatchDto;
import leejaewoo.server.book.dto.BookPostDto;
import leejaewoo.server.book.dto.BookResponseDto;
import leejaewoo.server.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> postBook(@RequestBody @Valid BookPostDto bookPostDto) {

        BookResponseDto result = bookService.createBook(bookPostDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PatchMapping("/{book-id}")
    public ResponseEntity<BookResponseDto> patchBook(@PathVariable(value = "book-id") Long bookId ,
                                                     @RequestBody @Valid BookPatchDto bookPatchDto) {
        BookResponseDto result = bookService.modifyBook(bookId, bookPatchDto);

        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
}
