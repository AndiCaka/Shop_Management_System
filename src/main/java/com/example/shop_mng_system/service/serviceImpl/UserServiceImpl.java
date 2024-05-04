package com.example.shop_mng_system.service.serviceImpl;

import com.example.shop_mng_system.entity.User;
import com.example.shop_mng_system.repository.UserRepository;
import com.example.shop_mng_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {

        User existingUser = userRepository.findById(id).get();
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true; // Return true if deletion is successful
        } catch (Exception e) {
            // Log the exception or handle it gracefully
            return false; // Return false if an error occurs during deletion
        }
    }
}
