package com.aki.reviewms.review.controller;

import com.aki.reviewms.review.entity.Review;
import com.aki.reviewms.review.entity.response.ResponseResult;
import com.aki.reviewms.review.service.ReviewServiceImpl;
import com.aki.reviewms.review.vo.AverageRatingVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewServiceImpl reviewService ;

    @GetMapping
    public ResponseResult<List<Review>> getAllReviews(@RequestParam("companyId") Long companyId) {

        return reviewService.getAllReviews(companyId) ;
    }

    @PostMapping
    public ResponseResult<Review> createNewReview(@RequestParam("companyId") Long companyId,
                                                  @RequestBody Review review) {

        return reviewService.createNewReview(companyId, review);

    }

    @GetMapping("/{reviewId}")
    public ResponseResult<Review> getReview(@PathVariable("reviewId") Long reviewId) {

        return reviewService.getReview(reviewId) ;
    }

    @PutMapping("/{reviewId}")
    public ResponseResult<Object> updateReview(
                             @PathVariable("reviewId") Long reviewId,
                             @RequestBody Review review) {

        return reviewService.updateReview(reviewId, review);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseResult<Object> deleteReview(@PathVariable("reviewId") Long reviewId) {

        return reviewService.deleteReview(reviewId);
    }

    @GetMapping(value = "/averageRating")
    public ResponseResult<AverageRatingVo> getAverageRating(@RequestParam("companyId") Long companyId) {

        return reviewService.getAverageRating(companyId) ;
    }
}
