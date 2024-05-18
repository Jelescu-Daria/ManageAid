package com.example.manageaid.repository;

import com.example.manageaid.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(value = "SELECT e.* FROM expenses e ORDER BY e.date_made DESC LIMIT 3", nativeQuery = true)
    List<Expense> getTopThreeMostRecentExpenses();

    List<Expense> findAllByOrderByDateMadeDesc();
}
