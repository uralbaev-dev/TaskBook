package com.example.taskbook.controller;


import com.example.taskbook.dto.BookDto;
import com.example.taskbook.entity.Book;
import com.example.taskbook.service.BookService;
import com.example.taskbook.service.BookServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;
    private final BookServiceV2 bookServiceV2;

    // -------------------- begin version 1 -------------------- //
    @GetMapping("/sorted")
    public ResponseEntity<?> getAllBooksSorted() {
        return ResponseEntity.ok(bookService.getAllBooksSorted());
    }

    @PostMapping
    public ResponseEntity<?> addBook(
            @RequestBody Book book
    ) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @GetMapping("/group-by-author")
    public ResponseEntity<?> getAllBooksGroupedByAuthor() {
        return ResponseEntity.ok(bookService.getAllBooksGroupedByAuthor());
    }

    @GetMapping("/character-count")
    public ResponseEntity<?> getCharacterCounts(
            @RequestParam("character") String character
    ) {
        return ResponseEntity.ok(bookService.getCharacterCounts(character));
    }
    // -------------------- end version 1 -------------------- //


    // -------------------- begin version 2 -------------------- //
    @GetMapping("/sorted-v2")
    public ResponseEntity<?> getAllBooksV2() {
        return ResponseEntity.ok(bookServiceV2.getAllBooksSorted());
    }

    @PostMapping("/v2")
    public ResponseEntity<?> addBookV2(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookServiceV2.addBook(bookDto));
    }

    @GetMapping("/group-by-author-v2")
    public ResponseEntity<?> getAllBooksGroupedByAuthorV2() {
        return ResponseEntity.ok(bookServiceV2.getAllBooksGroupedByAuthor());
    }

    @GetMapping("/character-count-v2")
    public ResponseEntity<?> getCharacterCountsV2(
            @RequestParam("character") String character
    ) {
        return ResponseEntity.ok(bookServiceV2.getCharacterCounts(character));
    }
    // -------------------- end version 2 -------------------- //
}
