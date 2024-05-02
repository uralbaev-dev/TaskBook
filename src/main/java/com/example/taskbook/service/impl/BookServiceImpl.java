package com.example.taskbook.service.impl;

import com.example.taskbook.entity.Book;
import com.example.taskbook.dto.CharacterCounts;
import com.example.taskbook.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> getAllBooksSorted() {
        log.info(">> getAllBooksSorted");

        String sql = "SELECT * FROM books ORDER BY title DESC";
        List<Book> booksSorted = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("description")
                )
        );

        if (!booksSorted.isEmpty()) {
            log.info("<< getAllBooksSorted");
            return booksSorted;
        }

        log.info("<< getAllBooksSorted | Not found");
        return null;
    }

    @Override
    public String addBook(Book book) {
        log.info(">> addBook");

        String sql = "INSERT INTO books (title, author, description) VALUES (?, ?, ?)";
        int i = jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getDescription());

        if (i > 0) {
            log.info("<< addBook");
            return "successfully added";
        }

        log.info("<< addBook | Error");
        return "Error";
    }

    @Override
    public Map<String, List<Book>> getAllBooksGroupedByAuthor() {
        log.info(">> getAllBooksGroupedByAuthor");

        String sql = "SELECT * FROM books";
        List<Book> allBooks = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("description")
                )
        );

        if (!allBooks.isEmpty()) {
            log.info("<< getAllBooksGroupedByAuthor");
            return allBooks
                    .stream()
                    .collect(Collectors.groupingBy(Book::getAuthor));
        }

        log.info("<< getAllBooksGroupedBy | Not found");
        return null;
    }

    @Override
    public List<CharacterCounts> getCharacterCounts(String character) {
        log.info(">> getCharacterCounts");

        String sql = "SELECT b.author, SUM(LENGTH(title) - LENGTH(REPLACE(LOWER(title),  LOWER(?), ''))) AS counts " +
                "FROM books b GROUP BY b.author ORDER BY counts DESC LIMIT 10";
        // FEATURE Response result counts > 0
        // String sql = "SELECT * FROM (SELECT b.author, SUM(LENGTH(title) - LENGTH(REPLACE(LOWER(title),  LOWER(?), ''))) AS counts " +
        //                "FROM books b GROUP BY b.author) AS subquery WHERE counts > 0 ORDER BY counts DESC LIMIT 10";
        List<CharacterCounts> characterCounts = jdbcTemplate.query(sql,
                (rs, rowNum) -> new CharacterCounts(
                        rs.getString("author"),
                        rs.getInt("counts")
                ),
                character
        );

        if (!characterCounts.isEmpty()) {
            log.info("<< getCharacterCounts");
            return characterCounts;
        }

        log.info("<< getCharacterCounts | Not found");
        return null;
    }
}
