package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.repository.DiscountServiceRepo;
import com.rade.dentistbookingsystem.services.DiscountSvService;
import org.springframework.stereotype.Service;

@Service
public class DiscountSvImpl implements DiscountSvService {
    DiscountServiceRepo discountServiceRepo;

    public DiscountSvImpl(DiscountServiceRepo discountServiceRepo) {
        this.discountServiceRepo = discountServiceRepo;
    }


}
