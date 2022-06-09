package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.domain.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Repository
public interface FeedBackRepo extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByServiceIdAndStatus(int id, int status, Pageable pageable);

    @Query(value =
            "SELECT Feedback.* " +
            "FROM Feedback, Account a " +
            "WHERE Feedback.account_id = a.id AND " +
                "(a.phone LIKE '%:phone%'  OR :phone IS NULL) AND " +
                "(Feedback.status = :status OR :status = -1) AND " +
                "(Feedback.service_id = :service_id OR :service_id = -1) AND " +
                "(DATEDIFF(day, Feedback.time, :time) = 0 OR :time IS NULL)",
            nativeQuery = true)
    List<Feedback> filterFeedback(@Param("phone") String phone,
                                  @Param("status") int status,
                                  @Param("service_id") int service_id,
                                  @Param("time") String time,
                                  Pageable pageable);
}
