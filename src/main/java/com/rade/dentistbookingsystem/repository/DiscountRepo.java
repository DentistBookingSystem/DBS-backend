package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Integer> {
    @Query(value =
            "SELECT Discount.* " +
                    "FROM Discount, Discount_Service ds " +
                    "WHERE Discount.id = ds.discount_id AND ds.service_id = ?1 AND Discount.status = 1 " +
                    "AND getdate() >= Discount.start_date AND getdate() <= Discount.end_date " +
                    "AND Discount.percentage >= ALL( " +
                    "SELECT Discount.percentage " +
                    "FROM Discount Discount, Discount_Service ds " +
                    "WHERE Discount.id = ds.discount_id AND ds.service_id = ?1 " +
                    "AND getdate() >= Discount.start_date AND getdate() <= Discount.end_date AND Discount.status = 1)",
            nativeQuery = true)
    Discount findAvailableByServiceId(Integer id);

    public Discount findByName(String name);

}
