package com.example.taskbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
