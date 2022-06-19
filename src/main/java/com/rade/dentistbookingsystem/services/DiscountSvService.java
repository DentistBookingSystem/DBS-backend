package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.DiscountService;
import com.rade.dentistbookingsystem.model.DiscountServiceDTO;

public interface DiscountSvService {
    <S extends com.rade.dentistbookingsystem.domain.DiscountService> S save(S entity);

    DiscountService addServiceDiscount(DiscountServiceDTO discountServiceDTO);

    DiscountService editServiceDiscount(DiscountServiceDTO discountServiceDTO);
}
