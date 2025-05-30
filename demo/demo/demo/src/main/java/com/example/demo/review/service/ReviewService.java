package com.example.demo.review.service;

import com.example.demo.review.dto.ReviewRequestDTO;
import com.example.demo.review.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    Long createReview(ReviewRequestDTO dto);
    List<ReviewResponseDTO> getReviewsByBook(Long bookId);
}
