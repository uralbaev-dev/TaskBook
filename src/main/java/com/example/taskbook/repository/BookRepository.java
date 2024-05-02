package com.example.taskbook.repository;

import com.example.taskbook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = """
            SELECT * FROM books as b ORDER BY b.title DESC
            """,
            nativeQuery = true
    )
    List<Book> getAllBooksSorted();

    @Query(value = """
            SELECT EXISTS (
              SELECT 1 FROM books
              WHERE title =:title AND author =:author
            )""",
            nativeQuery = true
    )
    boolean existsBookByTitleAndAuthor(@Param(value = "title") String title, @Param(value = "author") String author);

    @Query(value = """
               SELECT b.author, SUM(LENGTH(b.title) - LENGTH(REPLACE(LOWER(b.title),  LOWER(?1), ''))) AS counts
               FROM books b GROUP BY b.author ORDER BY counts DESC LIMIT 10
            """,
            /// FEATURE Response result counts > 0
            // SELECT * FROM (
            //   SELECT b.author,
            //   SUM(LENGTH(b.title) - LENGTH(REPLACE(LOWER(b.title), LOWER(?1), ''))) AS counts
            //   FROM books b
            //   GROUP BY b.author) AS subquery
            //   WHERE counts > 0
            //   ORDER BY counts DESC
            //   LIMIT 10
            nativeQuery = true
    )
    List<Map<String, Object>> getCharacterCounts(String character);

}
