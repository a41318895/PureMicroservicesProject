package com.aki.reviewms.review.messaging;

import com.aki.reviewms.review.dto.ReviewMessage;
import com.aki.reviewms.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Review review) {

        ReviewMessage reviewMessage = mapToReviewMessage(review) ;

        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
    }

    private ReviewMessage mapToReviewMessage(Review review) {

        return ReviewMessage.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .rating(review.getRating())
                .companyId(review.getCompanyId())
                .build();
    }
}
