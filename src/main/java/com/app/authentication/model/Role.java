package com.app.authentication.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.app.authentication.enumerator.UserRole;

@Entity
@Table(name = "role")
@SuppressWarnings("serial")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Role() {

    }

    public Role(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String getAuthority() {
        return userRole.toString();
    }

    public void setAuthority(String role) {
        this.userRole = UserRole.valueOf(role);
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void getUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
