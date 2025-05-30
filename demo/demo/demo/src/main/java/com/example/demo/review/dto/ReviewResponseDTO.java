package com.example.demo.review.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponseDTO {
    private Long reviewId;
    private int rating;
    private String content;
    private String user;
}
