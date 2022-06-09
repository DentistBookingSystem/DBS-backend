package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.componentform.StatusForFeedback;
import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.services.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("rade/admin/feedback")
public class FeedbackAdminController {
    @Autowired
    FeedBackService feedBackService;

    @GetMapping("{i}")
    public Page<Feedback> getFeedbackList(@PathVariable Integer i){
        if(i == null) i = 0;
        return feedBackService.findAll(PageRequest.of(i, 20, Sort.by("id").descending()));
    }

    @PostMapping("check")
    public ResponseEntity<?> checkFeedback(@RequestBody StatusForFeedback statusForFeedback){
        try {
            return ResponseEntity.ok(feedBackService.check(statusForFeedback.getStatus(), statusForFeedback.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
