package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.componentform.PageForFeedback;
import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.model.FeedbackDTO;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.FeedbackService;
import com.rade.dentistbookingsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @PostMapping("/filter")
    public List<Feedback> getFeedbackPage(@RequestBody PageForFeedback pageForFeedback){
        int page = pageForFeedback.getPage() - 1;
        Pageable pageable = PageRequest.of(page, 3, Sort.by("id").descending());
        return feedbackService.filterFeedback(
                pageForFeedback.getPhone(),
                pageForFeedback.getStatus(),
                pageForFeedback.getServiceId(),
                pageForFeedback.getTime(),
                pageable);
    }

    @PostMapping("/list")
    public List<Feedback> getListFeedback(){
        return feedbackService.findAllWithSort();
    }
    @GetMapping("filter")
    public List<Feedback> filterFeedbackForAdmin(@RequestBody PageForFeedback pageForFeedback){
        return feedbackService.filterFeedbackForAdmin(pageForFeedback.getPhone(),
                pageForFeedback.getStatus(),
                pageForFeedback.getServiceId(),
                pageForFeedback.getTime());
    }


    @PostMapping("/approve")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> approveFeedback(@RequestParam int feedbackId) {
        Feedback feedback = feedbackService.updateFeedbackStatus(feedbackId, 1);
        notificationService.createNotificationForApprovingFeedbackFromAdmin(feedback);
        return ResponseEntity.ok().build();


    }

    @PostMapping("/disapprove")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> disapproveFeedback(@RequestParam int feedbackId) {
        Feedback feedback = feedbackService.updateFeedbackStatus(feedbackId, 2);
        notificationService.createNotificationForDisapprovingFeedbackFromAdmin(feedback);
        int accountId = feedback.getAppointment().getAccount().getId();
        if (feedbackService.checkAccountToBanByFeedback(accountId)) {
            accountService.checkAccount(2, accountId);
        }
        return ResponseEntity.ok().build();
    }
}
