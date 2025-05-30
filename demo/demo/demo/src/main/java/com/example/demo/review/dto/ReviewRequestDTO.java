package com.example.demo.review.dto;

import com.example.demo.book.domain.Book;
import lombok.Data;

@Data
public class ReviewRequestDTO {
    private Long userId;
    private Book bookId;
    private int rating;
    private String content;
}
