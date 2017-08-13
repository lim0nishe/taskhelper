package com.itolla.test.taskhelper.service;

import com.itolla.test.taskhelper.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<User> getAllUsers();
    User save(User user);
    User update(User user);
}
