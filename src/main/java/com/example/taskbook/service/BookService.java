package com.example.taskbook.service;


import com.example.taskbook.entity.Book;
import com.example.taskbook.dto.CharacterCounts;

import java.util.List;
import java.util.Map;

public interface BookService {

    /**
     * 1-endpoint
     */
    List<Book> getAllBooksSorted();

    /**
     * 2-endpoint
     */
    String addBook(Book book);

    /**
     * 3-endpoint
     */
    Map<String, List<Book>> getAllBooksGroupedByAuthor();

    /**
     * 4-endpoint
     */
    List<CharacterCounts> getCharacterCounts(String character);
}
