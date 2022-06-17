package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Discount;
import com.rade.dentistbookingsystem.domain.DiscountService;
import com.rade.dentistbookingsystem.domain.DiscountServiceKey;
import com.rade.dentistbookingsystem.model.DiscountServiceDTO;
import com.rade.dentistbookingsystem.repository.DiscountServiceRepo;
import com.rade.dentistbookingsystem.services.DiscountSvService;
import com.rade.dentistbookingsystem.services.ServiceSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscountSvImpl implements DiscountSvService {
    DiscountServiceRepo discountServiceRepo;
    @Autowired
    ServiceSv servicesv;
    @Autowired
    com.rade.dentistbookingsystem.services.DiscountService discountSv;

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
        Optional<com.rade.dentistbookingsystem.domain.Service> service = servicesv.findById(discountServiceDTO.getService_id());
        Optional<Discount> discount = discountSv.findById(discountServiceDTO.getDiscount_id());
        System.out.println(discount.get().getId());
        System.out.println(service.get().getId());
        if (discount.isPresent() && service.isPresent()) {
            DiscountServiceKey discountServiceKey = new DiscountServiceKey();
            discountServiceKey.setDiscount_id(discount.get().getId());
            discountServiceKey.setService_id(service.get().getId());
            discountService.setDiscount(discount.get());
            discountService.setService(service.get());
            discountService.setId(discountServiceKey);
            return discountServiceRepo.save(discountService);
        } else throw new RuntimeException("Can not add");


    }

}
