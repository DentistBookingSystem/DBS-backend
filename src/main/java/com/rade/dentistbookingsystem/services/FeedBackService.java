package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.model.FeedbackDTO;
import org.springframework.data.domain.Page;

import java.text.ParseException;

public interface FeedBackService {
    Page<Feedback> findAllWithPagination();

    Feedback updateStatus(int id, int status) throws Exception;

    Feedback addFeedback(FeedbackDTO feedbackDTO) throws ParseException;
}
