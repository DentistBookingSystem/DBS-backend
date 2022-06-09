package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Integer> {
    public Branch findByUrl(String url);

    public Branch findByName(String name);

    @Query(value =
            "SELECT Branch.* " +
                    "FROM Branch " +
                    "WHERE Branch.id  = ?1 ",
            nativeQuery = true)
    public Branch findId(int id);

    public List<Branch> findByStatus(int status);
}
