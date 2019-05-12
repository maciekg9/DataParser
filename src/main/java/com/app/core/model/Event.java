package com.app.core.model;


import com.app.authentication.model.User;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name = "event")
@Table(name="event")
@Entity
public class Event implements Serializable {


    public Event(){

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Expose
    private String title;
    @Expose
    private Date start;
    @Expose
    private Date end;


    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setUser(User user) {
        this.user = user;
    }
    public void getUser(User user) {
        this.user = user;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
