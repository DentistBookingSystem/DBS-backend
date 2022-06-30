package com.rade.dentistbookingsystem.controller.staff;

import com.rade.dentistbookingsystem.componentform.PageForFeedback;
import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.FeedbackService;
import com.rade.dentistbookingsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/staff/feedback")
public class FeedbackStaffController {
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


    @PostMapping("approve/{id}")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> approveFeedback(@PathVariable int id) {
        final int APPROVE_FEEDBACK_STATUS = 1;
        Feedback feedback = feedbackService.updateFeedbackStatus(id, APPROVE_FEEDBACK_STATUS);
        notificationService.createNotificationForApprovingFeedbackFromAdmin(feedback);
        return ResponseEntity.ok().build();


    }

    @PostMapping("disapprove/{id}")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> disapproveFeedback(@PathVariable int id) {
        final int DISAPPROVE_FEEDBACK_STATUS = 1;
        Feedback feedback = feedbackService.updateFeedbackStatus(id, DISAPPROVE_FEEDBACK_STATUS);
        notificationService.createNotificationForDisapprovingFeedbackFromAdmin(feedback);
        int accountId = feedback.getAppointment().getAccount().getId();
        if (feedbackService.checkAccountToBanByFeedback(accountId)) {
            accountService.checkAccount(2, accountId);
        }
        return ResponseEntity.ok().build();
    }
}
