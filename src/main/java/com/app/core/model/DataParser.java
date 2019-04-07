package com.app.core.model;



import com.app.authentication.model.User;

import javax.persistence.*;


@Entity
@Table(name = "dataParser")

public class DataParser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (name = "action")
    private String action;

    @Column (name = "date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setUser(User user) {
        this.user = user;
    }
    public void getUser(User user) {
        this.user = user;
    }

    public DataParser(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}