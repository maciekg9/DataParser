package com.app.authentication.service;

import com.app.authentication.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Optional;

public interface UserService {

//    User getCurrentUser();

//    Optional<User> currentUser(Authentication auth);

    void save(User user);

    User findByUsername(String username);

//    User getUser();

    //    @Override
    //    public  Optional<User> currentUser(Authentication auth) {
    //        if (auth != null) {
    //            Object principal = auth.getPrincipal();
    //            if (principal instanceof User) // User is your user type that implements UserDetails
    //                return Optional.of((User) principal);
    //        }
    //        return Optional.empty();
    //    }
//    UserDetails currentUser(Principal principal);
}