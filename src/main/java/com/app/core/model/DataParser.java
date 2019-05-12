package com.app.core.model;


import com.app.authentication.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Entity (name = "data_parser")
@XmlRootElement(name = "dataParser")

@Table(name = "dataParser")

public class DataParser implements Serializable {


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Long id;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column (name = "action")
    @Expose
    @SerializedName("title")
    private String title;

    @Column (name = "date")
    @Expose
    @SerializedName("start")
    private String date;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @Expose
    @SerializedName("user_id")
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


}