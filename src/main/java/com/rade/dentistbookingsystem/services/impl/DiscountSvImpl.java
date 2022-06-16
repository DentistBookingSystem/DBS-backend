package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.DiscountService;
import com.rade.dentistbookingsystem.model.DiscountServiceDTO;
import com.rade.dentistbookingsystem.repository.DiscountServiceRepo;
import com.rade.dentistbookingsystem.services.DiscountSvService;
import com.rade.dentistbookingsystem.services.ServiceSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountSvImpl implements DiscountSvService {
    DiscountServiceRepo discountServiceRepo;
    @Autowired
    ServiceSv service;
    @Autowired
    com.rade.dentistbookingsystem.services.DiscountService discount;

    public DiscountSvImpl(DiscountServiceRepo discountServiceRepo) {
        this.discountServiceRepo = discountServiceRepo;
    }


    @Override
    public <S extends DiscountService> S save(S entity) {
        return discountServiceRepo.save(entity);
    }


    @Override
    public DiscountService addServiceDiscount(DiscountServiceDTO discountServiceDTO) {
        DiscountService discountService = new DiscountService();
        discountService.setService(service.findById(discountServiceDTO.getService_id()).orElseThrow(() -> new RuntimeException("Service not found")));
        discountService.setDiscount(discount.findById(discountServiceDTO.getDiscount_id()).orElseThrow(() -> new RuntimeException("discount not found")));
        return discountServiceRepo.save(discountService);

    }

}
