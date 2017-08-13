package com.itolla.test.taskhelper.controller;

import com.itolla.test.taskhelper.repository.UserRepository;
import com.itolla.test.taskhelper.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public User getUserInfo(Principal principal){
        return userRepository.findOneByUsername(principal.getName());
    }
}
