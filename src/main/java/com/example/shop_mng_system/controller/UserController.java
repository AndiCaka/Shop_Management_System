package com.example.shop_mng_system.controller;

import com.example.shop_mng_system.entity.User;
import com.example.shop_mng_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity containing the retrieved user or 404 if user is not found.
     */
    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get all users.
     *
     * @return ResponseEntity containing a list of all users.
     */
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Add a new user.
     *
     * @param user The user to be added.
     * @return ResponseEntity containing the added user.
     */
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User addedUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
    }

    /**
     * Update an existing user.
     *
     * @param id   The ID of the user to be updated.
     * @param user The updated user object.
     * @return ResponseEntity containing the updated user or 404 if user is not found.
     */
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a user by ID.
     *
     * @param id The ID of the user to be deleted.
     * @return ResponseEntity with 204 No Content if deletion is successful, or 404 if user is not found.
     */
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
