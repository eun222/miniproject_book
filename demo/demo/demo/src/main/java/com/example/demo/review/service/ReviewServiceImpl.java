package com.example.demo.review.service;

import com.example.demo.review.domain.Review;
import com.example.demo.review.dto.ReviewRequestDTO;
import com.example.demo.review.dto.ReviewResponseDTO;
import com.example.demo.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Long createReview(ReviewRequestDTO dto) {
        Review review = Review.builder()
                .userId(dto.getUserId())
                .book(dto.getBookId())
                .rating(dto.getRating())
                .content(dto.getContent())
                .build();
        return reviewRepository.save(review).getReviewId();
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByBook(Long bookId) {
        List<Review> reviews = reviewRepository.findByBookId(bookId);
        return reviews.stream()
                .map(review -> ReviewResponseDTO.builder()
                        .reviewId(review.getReviewId())
                        .rating(review.getRating())
                        .content(review.getContent())
                        .user("user" + review.getUserId() + "@example.com")
                        .build())
                .collect(Collectors.toList());
    }
}
