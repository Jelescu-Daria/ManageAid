package com.example.manageaid.repository;

import com.example.manageaid.model.Payment;
import com.example.manageaid.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT p.* FROM payments p ORDER BY p.date_made DESC LIMIT 3", nativeQuery = true)
    List<Payment> getTopThreeMostRecentPayments();

    List<Payment> findAllByOrderByDateMadeDesc();

    @Query(value = "SELECT p.* FROM payments p WHERE p.user_id = ?1 ORDER BY p.date_made DESC", nativeQuery = true)
    List<Payment> getPaymentsForUser(Long userId);
}
