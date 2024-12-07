package com.SpringMVC.service.impl;

import com.SpringMVC.domain.entity.User;
import com.SpringMVC.service.MailService;
import com.SpringMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MailService mailServiceImpl;

    public void setMailServiceImpl(MailService mailServiceImpl) {
        this.mailServiceImpl = mailServiceImpl;
    }

    // 模拟数据库
    private List<User> users = new ArrayList<>(List.of(
            new User(1, "bob@example.com", "password", "Bob"),
            new User(2, "alice@example.com", "password", "Alice"),
            new User(3, "tom@example.com", "password", "Tom")));

    public User getUser(long id) {
        return this.users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow();
    }

    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                mailServiceImpl.sendLoginMail(user);
                return user;
            }
        }
        throw new RuntimeException("login failed.");
    }

    public User register(String email, String password, String name) {
        users.forEach((user) -> {
            if (user.getEmail().equalsIgnoreCase(email)) {
                throw new RuntimeException("email exist.");
            }
        });
        User user = new User(users.stream().map(User::getId).max(Comparator.comparingInt(o -> o)).get(),
                email, password, name);
        users.add(user);
        mailServiceImpl.sendRegistrationMail(user);
        return user;
    }
}
