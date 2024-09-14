package com.aki.reviewms.review.service;

import com.aki.reviewms.review.entity.Review;
import com.aki.reviewms.review.entity.response.ResponseResult;
import com.aki.reviewms.review.vo.AverageRatingVo;

import java.util.List;

public interface ReviewService {

    ResponseResult<List<Review>> getAllReviews(Long companyId) ;

    ResponseResult<Review> getReview(Long reviewId);

    ResponseResult<Review> createNewReview(Long companyId, Review review) ;

    ResponseResult<Object> updateReview(Long reviewId, Review review) ;

    ResponseResult<Object> deleteReview(Long reviewId) ;

    ResponseResult<AverageRatingVo> getAverageRating(Long companyId);
}
