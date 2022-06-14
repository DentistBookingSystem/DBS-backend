package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.model.FeedbackDTO;
import com.rade.dentistbookingsystem.repository.AccountRepo;
import com.rade.dentistbookingsystem.repository.FeedbackRepo;
import com.rade.dentistbookingsystem.repository.ServiceRepo;
import com.rade.dentistbookingsystem.services.FeedbackService;
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
    ServiceRepo serviceRepo;

    @Autowired
    AccountRepo accountRepo;

    public FeedbackServiceImpl(FeedbackRepo feedBackRepo) {
        this.feedBackRepo = feedBackRepo;
    }

    public Feedback save(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback(
            serviceRepo.findId(feedbackDTO.getService_id()),
            accountRepo.findByPhone(feedbackDTO.getPhone()),
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
    public List<Feedback> findByServiceIdAndStatus(int id, int status, Pageable pageable) {
        return feedBackRepo.findByServiceIdAndStatus(id, status, pageable);
    }

    @Override
    public List<Feedback> filterFeedback(String phone, int status, int service_id, String time, Pageable pageable) {
        return feedBackRepo.filterFeedback(phone, status, service_id, time, pageable);
    }
}
