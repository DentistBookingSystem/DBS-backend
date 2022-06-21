package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.DiscountService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountServiceRepo extends JpaRepository<DiscountService, Integer> {
    void deleteAllByDiscount_Id(int discount_id);
}
