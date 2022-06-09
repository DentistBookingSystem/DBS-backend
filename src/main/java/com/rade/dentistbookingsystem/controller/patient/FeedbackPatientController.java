package com.rade.dentistbookingsystem.controller.patient;

import com.rade.dentistbookingsystem.model.FeedbackDTO;
import com.rade.dentistbookingsystem.services.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("rade/patient/feedback")
public class FeedbackPatientController {
    @Autowired
    FeedBackService feedBackService;
    @PostMapping("")
    public ResponseEntity<?> sendFeedback(@RequestBody FeedbackDTO feedbackDTO){
        try {
            return ResponseEntity.ok(feedBackService.save(feedbackDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
