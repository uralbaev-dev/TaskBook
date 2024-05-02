package com.example.taskbook.repository;

import com.example.taskbook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
            SELECT b FROM Book as b ORDER BY b.title DESC
            """)
    List<Book> getAllBooksSorted();
}
