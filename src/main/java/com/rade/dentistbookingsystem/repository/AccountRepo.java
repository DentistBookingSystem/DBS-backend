package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepo extends JpaRepository<Account, Integer> {
    @Query(value = "SELECT Account.* FROM Account  WHERE phone = ?1", nativeQuery = true)
    Account findByPhone(String phone);

    @Query(value = "SELECT Account.* FROM Account  WHERE id = ?1", nativeQuery = true)
    Account findId(int id);


}
