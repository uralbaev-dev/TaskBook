package com.example.taskbook.service;


import com.example.taskbook.entity.Book;
import com.example.taskbook.entity.CharacterCounts;

import java.util.List;
import java.util.Map;

public interface BookService {

    List<Book> getAllBooksSorted();

    String addBook(Book book);

    Map<String, List<Book>> getAllBooksGroupedByAuthor();

    List<CharacterCounts> getSymbolOccursMostTimes(String character);
}
