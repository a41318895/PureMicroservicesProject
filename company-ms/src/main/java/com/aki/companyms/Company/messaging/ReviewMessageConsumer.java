package com.aki.companyms.Company.messaging;

import com.aki.companyms.Company.dto.ReviewMessage;
import com.aki.companyms.Company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMessageConsumer {

    private final CompanyService companyService ;

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) {

        companyService.updateCompanyRating(reviewMessage);
    }

}
