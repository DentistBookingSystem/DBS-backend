package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiscountService {
    Discount findAvailableByServiceId(Integer id);


    List<Discount> findAll();

    Page<Discount> findAll(Pageable pageable);

    Page<Discount> findAllWithPagination();
}
