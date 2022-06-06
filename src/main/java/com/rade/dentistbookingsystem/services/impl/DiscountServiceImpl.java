package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Discount;
import com.rade.dentistbookingsystem.repository.DiscountRepo;
import com.rade.dentistbookingsystem.services.DiscountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    DiscountRepo discountRepo;

    public DiscountServiceImpl(DiscountRepo discountRepo) {
        this.discountRepo = discountRepo;
    }

    public Discount findAvailableByServiceId(Integer id) {
        return discountRepo.findAvailableByServiceId(id);
    }

    @Override
    public List<Discount> findAll() {
        return discountRepo.findAll();
    }

    @Override
    public Page<Discount> findAll(Pageable pageable) {
        return discountRepo.findAll(pageable);
    }

    @Override
    public Page<Discount> findAllWithPagination() {
        return discountRepo.findAll(PageRequest.of(0, 5));
    }
}
