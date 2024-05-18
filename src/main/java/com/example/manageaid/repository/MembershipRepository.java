package com.example.manageaid.repository;

import com.example.manageaid.model.Membership;
import com.example.manageaid.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> getMembershipByEndDateGreaterThanEqual (Date endDate);

    List<Membership> getMembershipByEndDateLessThan(Date endDate);

}
