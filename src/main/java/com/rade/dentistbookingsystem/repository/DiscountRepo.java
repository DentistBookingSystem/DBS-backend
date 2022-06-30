package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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


    List<Discount> findByStatus(int status);

    Page<Discount> findAllByStatus(int status, Pageable pageable);

    @Query(value =
            "SELECT Distinct  Discount.* " +
                    "FROM " +
                    "Discount, Discount_Service " +
                    "WHERE " +
                    "(Discount.name LIKE CONCAT('%',:name,'%') OR :name IS NULL OR :name ='') AND " +
                    "(Discount.status = :status OR :status = 0) AND " +
                    "(DATEDIFF(day,Discount.end_date, :endDate) = 0 OR :endDate IS NULL) AND " +
                    "(Discount_Service.service_id = :serviceId OR :serviceId = 0)"
            , nativeQuery = true)
    List<Discount> filterDiscount(
            @Param("status") int status,
            @Param("name") String name,
            @Param("endDate") Date endDate,
            @Param("serviceId") int serviceId);
}
