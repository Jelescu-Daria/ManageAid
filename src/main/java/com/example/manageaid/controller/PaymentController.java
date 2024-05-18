package com.example.manageaid.controller;

import com.example.manageaid.exception.EntityNotFoundException;
import com.example.manageaid.model.Payment;
import com.example.manageaid.model.Task;
import com.example.manageaid.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/payments")
    List<Payment> getAllPayments() {
        return paymentRepository.findAllByOrderByDateMadeDesc();
    }

    @GetMapping("/payments/recent")
    public List<Payment> getTop3PaymentsSortedByDateMade() {
        return paymentRepository.getTopThreeMostRecentPayments();
    }

    @GetMapping("/payments/{id}")
    Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @PostMapping("/payments")
    Payment addPayment(@RequestBody Payment newPayment) {
        return paymentRepository.save(newPayment);
    }

    @DeleteMapping("/payments/{id}")
    String deletePayment(@PathVariable Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        paymentRepository.deleteById(id);
        return "Entity with id " + id + " has been successfully deleted.";
    }

    @GetMapping("/users/{userId}/payments")
    List<Payment> getPaymentsForUser(@PathVariable Long userId) {
        return paymentRepository.getPaymentsForUser(userId);
    }

}
