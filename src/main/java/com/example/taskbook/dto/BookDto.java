package com.example.taskbook.dto;

import com.example.taskbook.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String description;

    public Book mapToBook() {
        Book book = new Book();
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setDescription(this.description);
        return book;
    }
 }
