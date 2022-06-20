package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.DiscountService;
import com.rade.dentistbookingsystem.domain.DiscountServiceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountServiceRepo extends JpaRepository<DiscountService, DiscountServiceKey> {

    public void deleteAllByDiscount_Id(int id);


}
