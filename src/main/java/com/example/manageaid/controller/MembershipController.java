package com.example.manageaid.controller;

import com.example.manageaid.exception.EntityNotFoundException;
import com.example.manageaid.model.Membership;
import com.example.manageaid.model.Payment;
import com.example.manageaid.repository.MembershipRepository;
import com.example.manageaid.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MembershipController {

    @Autowired
    private MembershipRepository membershipRepository;

    @GetMapping("/memberships")
    List<Membership> getAllMemberships() {
        return membershipRepository.findAll();
    }

    @GetMapping("/memberships/available")
    List<Membership> getAvailableMemberships() {
        return membershipRepository.getMembershipByEndDateGreaterThanEqual(DateUtils.removeTime(new Date()));
        // available membership = end date after current date or end date is current date
    }
    @GetMapping("/memberships/expired")
    List<Membership> getExpiredMemberships() {
        return membershipRepository.getMembershipByEndDateLessThan(DateUtils.removeTime(new Date()));
        // expired membership = end date before current date
    }

    @GetMapping("/memberships/{id}")
    Membership getMembershipById(@PathVariable Long id) {
        return membershipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @PostMapping("/memberships")
    Membership addMembership(@RequestBody Membership newMembership) {
        return membershipRepository.save(newMembership);
    }

    @PutMapping("/memberships/{id}")
    Membership updateMembership(@RequestBody Membership newMembership, @PathVariable Long id) {
        return membershipRepository.findById(id)
                .map(entity -> {
                    entity.setOffer(newMembership.getOffer());
                    entity.setStartDate(newMembership.getStartDate());
                    entity.setEndDate(newMembership.getEndDate());
                    entity.setCustomerInfo(newMembership.getCustomerInfo());
                    entity.setUser(newMembership.getUser());
                    return membershipRepository.save(entity);
                }).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @DeleteMapping("/memberships/{id}")
    String deleteMembership(@PathVariable Long id) {
        if (!membershipRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        membershipRepository.deleteById(id);
        return "Entity with id " + id + " has been successfully deleted.";
    }

    @GetMapping("/users/{userId}/memberships")
    List<Membership> getMembershipsForUser(@PathVariable Long userId) {
        return membershipRepository.getMembershipsForUser(userId);
    }

}
