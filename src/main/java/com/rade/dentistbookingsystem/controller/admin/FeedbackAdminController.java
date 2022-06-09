package com.rade.dentistbookingsystem.controller.admin;


import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.model.FeedbackDTO;
import com.rade.dentistbookingsystem.services.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rade/admin/feedback")
public class FeedbackAdminController {

    @Autowired
    FeedBackService feedBackService;


    @GetMapping()
    public Page<Feedback> getFeedbackList() {
        return feedBackService.findAllWithPagination();
    }

    @PostMapping("add")
    public ResponseEntity<?> addFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        try {
            return ResponseEntity.ok(feedBackService.addFeedback(feedbackDTO));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PostMapping("confirm")
    public ResponseEntity<?> confirmFeedBack(@RequestParam int status, @RequestParam int id) {
        try {
            return ResponseEntity.ok(feedBackService.updateStatus(id, status)); // accept hoặc reject dự trên update status

        } catch (Exception e) {
            e.printStackTrace();

        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

    }


}
