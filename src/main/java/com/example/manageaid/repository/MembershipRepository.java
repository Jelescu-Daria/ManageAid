package com.example.manageaid.repository;

import com.example.manageaid.model.Membership;
import com.example.manageaid.model.Payment;
import com.example.manageaid.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> getMembershipByEndDateGreaterThanEqual (Date endDate);

    List<Membership> getMembershipByEndDateLessThan(Date endDate);

    @Query(value = "SELECT m.* FROM memberships m WHERE m.user_id = ?1 ORDER BY m.end_date DESC", nativeQuery = true)
    List<Membership> getMembershipsForUser(Long userId);

}
