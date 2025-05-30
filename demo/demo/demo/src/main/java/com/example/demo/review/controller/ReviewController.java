package com.example.demo.review.controller;

import com.example.demo.review.dto.ReviewRequestDTO;
import com.example.demo.review.dto.ReviewResponseDTO;
import com.example.demo.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createReview(@RequestBody ReviewRequestDTO requestDTO) {
        Long reviewId = reviewService.createReview(requestDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "리뷰 작성 완료");
        response.put("review_id", reviewId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<?> getReviewsByBook(@PathVariable Long bookId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByBook(bookId);
        if (reviews.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("code", 404, "message", "리뷰 없음"));
        }
        return ResponseEntity.ok(Map.of("code", 200, "data", reviews));
    }
}
