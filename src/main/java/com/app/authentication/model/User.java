package com.app.authentication.model;

import com.app.core.model.DataParser;
import com.app.core.model.Event;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Cascade({ org.hibernate.annotations.CascadeType.ALL })
    private Collection<Role> userAuthorities = new HashSet<Role>();

    @Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;

    public Set<DataParser> getDataParsers() {
        return dataParsers;
    }

    public void setDataParsers(Set<DataParser> dataParsers) {
        this.dataParsers = dataParsers;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<DataParser> dataParsers;

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Event> events;

    @Override
    public Collection<Role> getAuthorities() {
        return userAuthorities;
    }

    public void setAuthorities(Collection<Role> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public void insertNewAuthority(Role authority) {
        authority.setUser(this);
        userAuthorities.add(authority);
    }

    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}