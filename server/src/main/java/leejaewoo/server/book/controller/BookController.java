package leejaewoo.server.book.controller;

import leejaewoo.server.book.dto.BookPatchDto;
import leejaewoo.server.book.dto.BookPostDto;
import leejaewoo.server.book.dto.BookResponseDto;
import leejaewoo.server.book.service.BookService;
import leejaewoo.server.global.exception.book.BookDuplicateException;
import leejaewoo.server.global.exception.book.BookNotFoundException;
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
    public ResponseEntity postBook(@RequestBody @Valid BookPostDto bookPostDto) {

        BookResponseDto result;

        try {
             result = bookService.createBook(bookPostDto);
        } catch (BookDuplicateException bde) {
//            return new 사용 지양
//            return new ResponseEntity<>(bde.getMessage(), bde.getHttpStatus());
            return ResponseEntity.status(bde.getHttpStatus()).body(bde.getMessage());
        }
//        return new 사용 지양
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping("/{book-id}")
    public ResponseEntity patchBook(@PathVariable(value = "book-id") Long bookId ,
                                                     @RequestBody @Valid BookPatchDto bookPatchDto) {
        BookResponseDto result;

        try {
             result = bookService.modifyBook(bookId, bookPatchDto);
        } catch (BookNotFoundException bnfe) {
//            return new 사용 지양
//            return new ResponseEntity<>(bnfe.getMessage(), bnfe.getHttpStatus());
            return ResponseEntity.status(bnfe.getHttpStatus()).body(bnfe.getMessage());
        }

//        return new 사용 지양
//        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }
}
