package com.SpringMVC.service;

import com.SpringMVC.domain.entity.User;

public interface UserService {

    User login(String email, String password);

    User register(String email, String password, String name);
}
