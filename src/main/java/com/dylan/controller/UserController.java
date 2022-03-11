package com.dylan.controller;


import com.dylan.model.User;
import com.dylan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;


    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }


    @GetMapping("users")
    public List<User> getAllUsers() {

       return userService.getAllUsers();
    }

    @GetMapping("user/{user_id}")
    public User getUser(@PathVariable Integer user_id){

        return userService.getUser(user_id);
    }

    @PostMapping("addUser")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);


    }

    @DeleteMapping("deleteUser")
    public void deleteUser(@RequestBody User user) {

        userService.deleteUser(user);
    }
    @PostMapping("updateUser")
public void updateUser(@RequestBody User user){

        userService.updateUser(user);
    }








}
