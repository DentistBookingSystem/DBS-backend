package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.model.FeedbackDTO;
import com.rade.dentistbookingsystem.repository.FeedbackRepo;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.AppointmentService;
import com.rade.dentistbookingsystem.services.FeedbackService;
import com.rade.dentistbookingsystem.services.ServiceSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    FeedbackRepo feedBackRepo;

    @Autowired
    ServiceSv serviceSv;

    @Autowired
    AccountService accountService;
    @Autowired
    AppointmentService appointmentService;

    public FeedbackServiceImpl(FeedbackRepo feedBackRepo) {
        this.feedBackRepo = feedBackRepo;
    }
    @Override
    public Feedback save(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback(
            appointmentService.findId(feedbackDTO.getAppointmentId()),
            new Date(),
            feedbackDTO.getContent(),
            0);
        return feedBackRepo.save(feedback);
    }

    @Override
    public Page<Feedback> findAll(Pageable pageable) {
        return feedBackRepo.findAll(pageable);
    }

    public <S extends Feedback> S save(S entity) {
        return feedBackRepo.save(entity);
    }

    @Override
    public Feedback check(Integer status, Integer id) {
        Optional<Feedback> feedbackData = feedBackRepo.findById(id);
        if(feedbackData.isPresent()){
            Feedback feedback = feedbackData.get();
            feedback.setStatus(status);
            return save(feedback);
        }
        return null;
    }

    @Override
    public List<Feedback> filterFeedback(String phone, int status, int serviceId, String time, Pageable pageable) {
        return feedBackRepo.filterFeedback(phone, status, serviceId, time, pageable);
    }

    @Override
    public boolean checkAccountToBanByFeedback(int accountId) {
        return feedBackRepo.checkAccountToBanByFeedback(accountId);
    }
}
