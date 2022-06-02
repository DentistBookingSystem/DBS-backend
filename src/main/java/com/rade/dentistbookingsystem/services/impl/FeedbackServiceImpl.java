package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.repository.FeedBackRepo;
import com.rade.dentistbookingsystem.services.FeedBackService;

public class FeedbackServiceImpl implements FeedBackService {
    FeedBackRepo feedBackRepo;

    public FeedbackServiceImpl(FeedBackRepo feedBackRepo) {
        this.feedBackRepo = feedBackRepo;
    }
}
