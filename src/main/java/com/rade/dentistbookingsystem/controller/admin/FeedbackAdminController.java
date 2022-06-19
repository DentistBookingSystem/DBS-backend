package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("rade/admin/feedback")
public class FeedbackAdminController {
    @Autowired
    FeedbackService feedbackService;


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
}
