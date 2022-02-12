package com.criptocloudapi.cloud.repository;

import com.criptocloudapi.cloud.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    public Optional<List<Expense>> findByUser(String codUser);
}
