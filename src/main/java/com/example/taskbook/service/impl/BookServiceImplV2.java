package com.example.taskbook.service.impl;

import com.example.taskbook.dto.BookDto;
import com.example.taskbook.dto.ApiResponse;
import com.example.taskbook.entity.Book;
import com.example.taskbook.repository.BookRepository;
import com.example.taskbook.service.BookServiceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@Primary
@RequiredArgsConstructor
public class BookServiceImplV2 implements BookServiceV2 {

    private final BookRepository bookRepository;

    @Override
    public ApiResponse<List<Book>> getAllBooksSorted() {
        log.info(">> getAllBooksSorted");
        List<Book> booksSorted;

        try {
            booksSorted = bookRepository.getAllBooksSorted();
            if (!booksSorted.isEmpty()) {
                log.info("<< getAllBooksSorted");
                return ApiResponse.success(booksSorted);
            }

            log.info("<< getAllBooksSorted | Not Found");
            return ApiResponse.error("Not found", HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            log.error("<< getAllBooksSorted");
            return ApiResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<String> addBook(BookDto bookDto) {
        log.info(">> addBook");

        try {
            if (!bookRepository.existsBookByTitleAndAuthor(bookDto.getTitle(), bookDto.getAuthor())) {
                bookRepository.save(bookDto.mapToBook());
                log.info("<< addBook | successfully saved");
                return ApiResponse.success("Saved", HttpStatus.CREATED.value());
            }

            log.info("<< addBook Book already is exist");
            return ApiResponse.error("Book already exists", HttpStatus.CONFLICT.value());
        } catch (Exception e) {
            log.error("<< addBook");
            return ApiResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    @Override
    public Map<String, List<Book>> getAllBooksGroupedByAuthor() {
        log.info(">> getAllBooksGroupedByAuthor");
        return bookRepository.findAll().stream().collect(Collectors.groupingBy(Book::getAuthor));
    }

    @Override
    public List<Map<String, Object>> getCharacterCounts(String character) {
        log.info(">> getCharacterCounts character: {}", character);
        return bookRepository.getCharacterCounts(character);
    }


}
