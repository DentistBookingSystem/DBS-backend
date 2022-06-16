package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.DiscountService;
import com.rade.dentistbookingsystem.model.DiscountServiceDTO;

public interface DiscountSvService {
    <S extends DiscountService> S save(S entity);

    DiscountService addServiceDiscount(DiscountServiceDTO discountServiceDTO);
}
