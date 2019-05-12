package com.app.core.repository;

import com.app.authentication.model.User;
import com.app.core.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("calendarRepository")
public interface CalendarRepository extends JpaRepository<Event, Long> {
    public List<Event> findByUser(User user);

}
