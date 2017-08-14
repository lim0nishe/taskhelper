package com.itolla.test.taskhelper.controller;

import com.itolla.test.taskhelper.dto.UserDto;
import com.itolla.test.taskhelper.repository.UserRepository;
import com.itolla.test.taskhelper.model.User;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET)
    public UserDto getUserInfo(Principal principal){
        return modelMapper.map(userRepository.findOneByUsername(principal.getName()), UserDto.class);
    }
}
