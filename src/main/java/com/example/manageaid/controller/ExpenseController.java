package com.example.manageaid.controller;

import com.example.manageaid.exception.EntityNotFoundException;
import com.example.manageaid.model.Expense;
import com.example.manageaid.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/expenses")
    List<Expense> getAllExpenses() {
        return expenseRepository.findAllByOrderByDateMadeDesc();
    }

    @GetMapping("/expenses/recent")
    public List<Expense> getTop3ExpensesSortedByDateMade() {
        return expenseRepository.getTopThreeMostRecentExpenses();
    }

    @GetMapping("/expenses/{id}")
    Expense getExpenseById(@PathVariable Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @PostMapping("/expenses")
    Expense addExpense(@RequestBody Expense newExpense) {
        return expenseRepository.save(newExpense);
    }

    @DeleteMapping("/expenses/{id}")
    String deleteExpense(@PathVariable Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        expenseRepository.deleteById(id);
        return "Entity with id " + id + " has been successfully deleted.";
    }

}
