package com.example.taskbook.entity;

import com.example.taskbook.dto.BookDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@Entity
public class Book {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @SequenceGenerator(
            name = "bookIdGenerator",
            sequenceName = "book_id_seq",
            schema = "public",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookIdGenerator")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;


    public BookDto mapToBookDto() {
        BookDto bookDto = new BookDto();
        bookDto.setId(this.id);
        bookDto.setTitle(this.title);
        bookDto.setAuthor(this.author);
        bookDto.setDescription(this.description);
        return bookDto;
    }

}
