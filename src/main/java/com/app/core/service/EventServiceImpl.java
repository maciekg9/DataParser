package com.app.core.service;

import com.app.authentication.model.User;
import com.app.authentication.repository.UserRepository;
import com.app.core.model.Event;
import com.app.core.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveEvent(Event event){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByUsername(name);
        event.setTitle(event.getTitle());
        event.setStart(event.getStart());
        event.setEnd(event.getEnd());
        event.setUser(user);
        calendarRepository.save(event);
    }
}
