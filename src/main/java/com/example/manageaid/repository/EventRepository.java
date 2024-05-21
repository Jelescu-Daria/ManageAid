package com.example.manageaid.repository;

import com.example.manageaid.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT e.* from events e WHERE e.user_id = ?1 ORDER BY e.start_time ", nativeQuery = true)
    List<Event> getEventsForUser(Long userId);
}
