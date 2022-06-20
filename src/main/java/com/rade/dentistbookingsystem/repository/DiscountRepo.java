package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    Optional<Discount> findById(Integer integer);

    public Discount findByName(String name);

    public List<Discount> findByStatus(int status);

    public Page<Discount> findAllByStatus(int status, Pageable pageable);
}
