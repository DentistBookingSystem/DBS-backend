package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.repository.DiscountServiceRepo;
import com.rade.dentistbookingsystem.services.DiscountSvService;

public class DiscountSvImpl implements DiscountSvService {
    DiscountServiceRepo discountServiceRepo;

    public DiscountSvImpl(DiscountServiceRepo discountServiceRepo) {
        this.discountServiceRepo = discountServiceRepo;
    }


}
