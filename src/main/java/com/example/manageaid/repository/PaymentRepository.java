package com.example.manageaid.repository;

import com.example.manageaid.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT p.* FROM payments p ORDER BY p.date_made DESC LIMIT 3", nativeQuery = true)
    List<Payment> getTopThreeMostRecentPayments();

}
