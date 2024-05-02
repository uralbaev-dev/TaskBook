package com.example.taskbook.controller;


import com.example.taskbook.entity.Book;
import com.example.taskbook.service.BookService;
import com.example.taskbook.service.BookServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookServiceV2 bookServiceV2;

    @GetMapping("/sorted")
    public ResponseEntity<?> getAllBooksSorted() {
        return ResponseEntity.ok(bookService.getAllBooksSorted());
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @GetMapping("/group-by-author")
    public ResponseEntity<?> getAllBooksGroupedByAuthor() {
        return ResponseEntity.ok(bookService.getAllBooksGroupedByAuthor());
    }

//    @GetMapping("/authors-with-char")
//    public List<CharacterCounts> getAuthorsWithChar(@RequestParam char character) {
//        String sql = "SELECT author, COUNT(*) AS count FROM books WHERE LOWER(title) LIKE ?";
//        String param = "%" + Character.toLowerCase(character) + "%";
//        return jdbcTemplate.query(sql, new Object[]{param}, (rs, rowNum) ->
//                        new CharacterCounts(rs.getString("author"), rs.getInt("count")))
//                .sorted((a, b) -> Long.compare(b.getCount(), a.getCount()))
//                .limit(10)
//                .collect(Collectors.toList());
//    }

    @GetMapping("/character-count")
    public ResponseEntity<?> getCharacterCount(@RequestParam("character") String character) {
        return ResponseEntity.ok(bookService.getSymbolOccursMostTimes(character));
    }

    @GetMapping("/sortedV2")
    public ResponseEntity<?> getAllBooksV2() {
        return ResponseEntity.ok(bookServiceV2.getAllBooksSorted());
    }

}
