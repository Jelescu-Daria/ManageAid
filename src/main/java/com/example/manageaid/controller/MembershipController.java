package com.example.manageaid.controller;

import com.example.manageaid.exception.EntityNotFoundException;
import com.example.manageaid.model.Membership;
import com.example.manageaid.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
