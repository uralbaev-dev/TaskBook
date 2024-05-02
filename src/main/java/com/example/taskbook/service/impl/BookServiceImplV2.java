package com.example.taskbook.service.impl;

import com.example.taskbook.entity.ApiResponse;
import com.example.taskbook.entity.Book;
import com.example.taskbook.repository.BookRepository;
import com.example.taskbook.service.BookServiceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

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
            return ApiResponse.error(404, "Not found");
        } catch (Exception e) {
            log.error("<< getAllBooksSorted");
            return ApiResponse.error(500, e.getMessage());
        }
    }
}
