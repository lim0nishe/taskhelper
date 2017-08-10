package com.itolla.test.taskhelper.Services;

import com.itolla.test.taskhelper.Repositories.UserRepository;
import com.itolla.test.taskhelper.Repositories.UserRepositoryCustom;
import com.itolla.test.taskhelper.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return ((UserRepositoryCustom)userRepository).save(user);
    }

    @Override
    public User update(User user) {
        return ((UserRepositoryCustom)userRepository).save(user);
    }
}
