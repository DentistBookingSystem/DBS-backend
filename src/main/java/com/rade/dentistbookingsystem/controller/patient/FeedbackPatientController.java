package com.rade.dentistbookingsystem.controller.patient;

import com.rade.dentistbookingsystem.componentform.PageForFeedback;
import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.model.FeedbackDTO;
import com.rade.dentistbookingsystem.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/patient/feedback")
public class FeedbackPatientController {
    @Autowired
    FeedbackService feedbackService;
    @PostMapping("send")
    public ResponseEntity<?> sendFeedback(@RequestBody FeedbackDTO feedbackDTO){
        try {
            return ResponseEntity.ok(feedbackService.save(feedbackDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }


}
