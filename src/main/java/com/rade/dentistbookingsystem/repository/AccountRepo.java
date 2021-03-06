package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepo extends JpaRepository<Account, Integer> {
    @Query(value = "SELECT * FROM Account  WHERE phone = ?1", nativeQuery = true)
    Account findByPhone(String phone);
}
