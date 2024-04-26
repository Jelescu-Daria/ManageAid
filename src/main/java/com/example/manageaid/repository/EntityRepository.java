package com.example.manageaid.repository;

import com.example.manageaid.model.Ent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EntityRepository extends JpaRepository<Ent, Long> {
}
