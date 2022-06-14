package com.rade.dentistbookingsystem.controller;

import com.rade.dentistbookingsystem.componentform.PageForFeedback;
import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
    @PostMapping("")
    public List<Feedback> getFeedbackPage(@RequestBody PageForFeedback pageForFeedback){
        short available = 1;
        int page = pageForFeedback.getPage();
        int service_id = pageForFeedback.getService_id();
        Pageable pageable = PageRequest.of(pageForFeedback.getPage() - 1, 3, Sort.by("id").descending());
        return feedbackService.filterFeedback(null, available, pageForFeedback.getService_id(), null, pageable);
    }
}
