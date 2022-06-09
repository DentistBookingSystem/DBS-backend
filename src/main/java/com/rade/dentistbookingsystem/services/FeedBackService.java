package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.model.FeedbackDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface FeedBackService {
    abstract Feedback save(FeedbackDTO feedbackDTO);

    Page<Feedback> findAll(Pageable pageable);

    Feedback check(Integer status, Integer id);

    List<Feedback> findByServiceIdAndStatus(int id, int status, Pageable pageable);

    List<Feedback> filterFeedback(String phone, int status, int service_id, String time, Pageable pageable);

}
