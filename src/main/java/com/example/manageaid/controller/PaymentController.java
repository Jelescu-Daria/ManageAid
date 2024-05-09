package com.example.manageaid.controller;

import com.example.manageaid.exception.EntityNotFoundException;
import com.example.manageaid.model.Payment;
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
        return paymentRepository.findAll();
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

    @PutMapping("/payments/{id}")
    Payment updatePayment(@RequestBody Payment newPayment, @PathVariable Long id) {
        return paymentRepository.findById(id)
                .map(entity -> {
                    entity.setSum(newPayment.getSum());
                    entity.setDetails(newPayment.getDetails());
                    entity.setDateMade(newPayment.getDateMade());
                    entity.setPayerInfo(newPayment.getPayerInfo());
                    entity.setUser(newPayment.getUser());
                    return paymentRepository.save(entity);
                }).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @DeleteMapping("/payments/{id}")
    String deletePayment(@PathVariable Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        paymentRepository.deleteById(id);
        return "Entity with id " + id + " has been successfully deleted.";
    }

}
