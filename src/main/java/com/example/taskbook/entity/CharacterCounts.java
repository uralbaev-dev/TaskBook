package com.example.taskbook.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @className: CharacterCounts
 * @date: 01.05.2024
 * @author: Uralbaev Diyorbek
 */

@Getter
@AllArgsConstructor
public class CharacterCounts {

    private String author;
    private Integer counts;
}
