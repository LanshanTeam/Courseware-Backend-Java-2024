package com.SpringMVC.service;

import com.SpringMVC.domain.entity.User;

public interface MailService {

    void sendLoginMail(User user);

    void sendRegistrationMail(User user);

}
