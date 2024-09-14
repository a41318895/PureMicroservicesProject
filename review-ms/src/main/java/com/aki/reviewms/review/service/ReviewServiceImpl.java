package com.aki.reviewms.review.service;

import com.aki.reviewms.review.entity.Review;
import com.aki.reviewms.review.entity.response.ResponseResult;
import com.aki.reviewms.review.exception.customedException.ServiceException;
import com.aki.reviewms.review.httpCode.HttpCodeEnum;
import com.aki.reviewms.review.messaging.ReviewMessageProducer;
import com.aki.reviewms.review.repository.ReviewRepository;
import com.aki.reviewms.review.vo.AverageRatingVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository ;
    private final ReviewMessageProducer reviewMessageProducer ;

    @Override
    public ResponseResult<List<Review>> getAllReviews(Long companyId) {

        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        log.info("Got list of all reviews successfully !") ;

        return ResponseResult.successWithAllStatus(HttpCodeEnum.SUCCESS, reviews) ;
    }

    @Override
    public ResponseResult<Review> getReview(Long reviewId) {

        Review foundReview = isReviewExist(reviewId);
        log.info("Got the corresponding review [ id = {} ] successfully !", foundReview.getId()) ;

        return ResponseResult.successWithAllStatus(HttpCodeEnum.SUCCESS, foundReview) ;
    }

    @Override
    public ResponseResult<Review> createNewReview(Long companyId, Review review) {

        if(companyId != null && review != null) {

            review.setCompanyId(companyId);
            Review reviewSaved = reviewRepository.save(review);
            log.info("Created a new review successfully !") ;

            reviewMessageProducer.sendMessage(reviewSaved);

            return ResponseResult.successWithAllStatus(HttpCodeEnum.SUCCESS, reviewSaved) ;
        }

        log.info("Created a new review fail !") ;
        return ResponseResult.errorWithCodeAndMsg(HttpCodeEnum.REVIEW_CREATE_FAIL) ;
    }

    @Override
    public ResponseResult<Object> updateReview(Long reviewId, Review reviewRequest) {

        Review foundReview = isReviewExist(reviewId) ;

        reviewRepository.save(foundReview) ;
        log.info("Updated the information of review [ id = {} ] successfully !", foundReview.getId()) ;

        return ResponseResult.successWithAllStatus(HttpCodeEnum.ACCEPTED, foundReview) ;
    }

    @Override
    public ResponseResult<Object> deleteReview(Long reviewId) {

        Review foundReview = isReviewExist(reviewId) ;

        reviewRepository.deleteById(reviewId);
        log.info("Deleted a review [ id = {} ] successfully !", foundReview.getId()) ;

        return ResponseResult.successWithAllStatus(HttpCodeEnum.NO_CONTENT, foundReview) ;
    }

    @Override
    public ResponseResult<AverageRatingVo> getAverageRating(Long companyId) {

        List<Review> reviews = getAllReviews(companyId).getData() ;

        Double averageRatingScore =
                reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0) ;

        Double avgTransformed = roundToTwoDecimalPlaces(averageRatingScore);

        return ResponseResult.successWithAllStatus(HttpCodeEnum.SUCCESS, new AverageRatingVo(avgTransformed)) ;
    }

    private Double roundToTwoDecimalPlaces(Double transformingDoubleValue) {

        DecimalFormat df = new DecimalFormat("#.##");
        String format = df.format(transformingDoubleValue);

        return Double.parseDouble(format);
    }

    private Review isReviewExist(Long reviewId) {

        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ServiceException(HttpCodeEnum.REVIEW_NOT_FOUND)) ;
    }
}
