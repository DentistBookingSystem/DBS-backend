package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.componentform.PageForFeedback;
import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.FeedbackService;
import com.rade.dentistbookingsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/admin/feedback")
public class FeedbackAdminController {
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    AccountService accountService;

    @PostMapping("list")
    public List<Feedback> getListFeedback() {
        return feedbackService.findAllWithSort();
    }

    @PostMapping("filter")
    public List<Feedback> filterFeedbackForAdmin(@RequestBody PageForFeedback pageForFeedback) {
        return feedbackService.filterFeedbackForAdmin(pageForFeedback.getPhone(),
                pageForFeedback.getStatus(),
                pageForFeedback.getServiceId(),
                pageForFeedback.getTime());
    }
}
