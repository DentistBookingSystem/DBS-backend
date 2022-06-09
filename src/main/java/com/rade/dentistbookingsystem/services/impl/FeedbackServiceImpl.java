package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.model.FeedbackDTO;
import com.rade.dentistbookingsystem.repository.FeedBackRepo;
import com.rade.dentistbookingsystem.services.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@Service
public class FeedbackServiceImpl implements FeedBackService {
    FeedBackRepo feedBackRepo;
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    ServiceSvImpl serviceSv;

    public FeedbackServiceImpl(FeedBackRepo feedBackRepo) {
        this.feedBackRepo = feedBackRepo;
    }


    @Override
    public Page<Feedback> findAllWithPagination() {
        return feedBackRepo.findAll(PageRequest.of(0, 5));

    }

    @Override
    public Feedback updateStatus(int id, int status) throws Exception {

        if (feedBackRepo.existsById(id)) {
            Feedback feedback = feedBackRepo.getById(id);
            feedback.setStatus(status);
            return feedBackRepo.save(feedback);
        } else {
            throw new Exception("feed back not found!!!");
        }
    }

    @Override
    public Feedback addFeedback(FeedbackDTO feedbackDTO) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Feedback feedback = new Feedback();
        feedback.setStatus(0);
        feedback.setAccount(accountService.findId(feedbackDTO.getAccount_id()));
        feedback.setService(serviceSv.findId(feedbackDTO.getService_id()));
        feedback.setContent(feedbackDTO.getContent());
        feedback.setTime(sdf.parse(feedbackDTO.getTime()));
        return feedBackRepo.save(feedback);

    }

}
