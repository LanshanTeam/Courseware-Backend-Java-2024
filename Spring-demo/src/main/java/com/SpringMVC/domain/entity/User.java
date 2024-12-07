package com.SpringMVC.domain.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;

    public User(Integer id, String email, String password, String name) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
