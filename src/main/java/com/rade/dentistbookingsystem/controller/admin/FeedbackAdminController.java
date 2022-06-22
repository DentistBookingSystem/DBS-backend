package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.services.FeedbackService;
import com.rade.dentistbookingsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("rade/admin/feedback")
public class FeedbackAdminController {
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    NotificationService notificationService;
//    @GetMapping("{i}")
//    public Page<Feedback> getFeedbackList(@PathVariable Integer i){
//        if(i == null) i = 0;
//        return feedBackService.findAll(PageRequest.of(i, 20, Sort.by("id").descending()));
//    }

//    @PostMapping("check")
//    public ResponseEntity<?> checkFeedback(@RequestBody StatusForFeedback statusForFeedback){
//        try {
//            return ResponseEntity.ok(feedBackService.check(statusForFeedback.getStatus(), statusForFeedback.getId()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
//    }

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
        return ResponseEntity.ok().build();


    }
}
