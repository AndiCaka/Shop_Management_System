package com.example.shop_mng_system.service;

import com.example.shop_mng_system.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    List<User> getAllUsers();

    User addUser(User user);

    User updateUser(Long id, User user);

    boolean deleteUser(Long id);
}
