package com.example.taskbook.service;


import com.example.taskbook.dto.BookDto;
import com.example.taskbook.dto.ApiResponse;
import com.example.taskbook.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookServiceV2 {

    /**
     * 1-endpoint
     */
    ApiResponse<List<Book>> getAllBooksSorted();

    /**
     * 2-endpoint
     */
    ApiResponse<String> addBook(BookDto bookDto);

    /**
     * 3-endpoint
     */
    Map<String, List<Book>> getAllBooksGroupedByAuthor();

    /**
     * 4-endpoint
     */
    List<Map<String, Object>> getCharacterCounts(String character);
}
