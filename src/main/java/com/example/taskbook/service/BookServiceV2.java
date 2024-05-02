package com.example.taskbook.service;


import com.example.taskbook.entity.ApiResponse;
import com.example.taskbook.entity.Book;

import java.util.List;

public interface BookServiceV2 {

    ApiResponse<List<Book>> getAllBooksSorted();
}
