package com.itolla.test.taskhelper.Services;

import com.itolla.test.taskhelper.models.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<User> getAllUsers();
    User save(User user);
    User update(User user);
}
