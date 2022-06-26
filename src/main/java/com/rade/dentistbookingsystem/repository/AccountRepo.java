package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.componentform.AccountAndViolationTimes;
import com.rade.dentistbookingsystem.domain.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, Integer> {
    @Query(value = "SELECT Account.* FROM Account  WHERE phone = ?1", nativeQuery = true)
    Account findByPhone(String phone);

    @Query(value = "SELECT Account.* FROM Account  WHERE id = ?1", nativeQuery = true)
    Account findId(int id);

    @Query(value =
            "SELECT Account.*" +
            "FROM Account " +
            "LEFT JOIN " +
            "(" +
                "select a.id, count(apm.account_id) as count_apm " +
                "from Account as a " +
                "left join Appointment apm on a.id = apm.account_id and datediff(MONTH, apm.appointment_date, getdate()) <= 3 and apm.status = 2 " +
                "group by a.id, apm.account_id" +
            ") AS apm ON Account.id = apm.id " +
            "LEFT JOIN " +
            "(" +
                "select a.id, count(f.account_id) as count_f " +
                "from Account as a " +
                "left join Feedback f on a.id = f.account_id and datediff(MONTH, f.time, getdate()) <= 3 and f.status = 2 " +
                "group by a.id, f.status" +
            ") AS f ON Account.id = f.id " +
            "WHERE apm.count_apm + f.count_f > 4", nativeQuery = true)
    List<Account> findAccountViolated(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE Account SET status = :status " +
                    "WHERE id = :id",
            nativeQuery = true)
    void checkAccount(@Param("status") Integer status, @Param("id") Integer id);

    @Query(
            value = "SELECT a from Account a where a.role.id = ?1 Or a.role.id = ?2")
    List<Account> findAllStaffAndUser(int staff, int customer);
}

