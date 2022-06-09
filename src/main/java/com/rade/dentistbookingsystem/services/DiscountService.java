package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.Discount;

public interface DiscountService {
    Discount findAvailableByServiceId(Integer id);

}
