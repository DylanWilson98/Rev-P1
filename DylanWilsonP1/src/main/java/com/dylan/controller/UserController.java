package com.dylan.controller;

import com.dylan.model.User;
import com.dylan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("users")
    public List<User> getUsers() {

        return userService.findAllUsers();
    }


    @GetMapping("user/{user_id}")
    public User getUserById(@PathVariable int user_id){

        return userService.getUserById(user_id);
    }

    @PostMapping("addUser")
    public User addUser(@RequestBody User user){

        return userService.addUser(user);
    }

    @PostMapping("updateUser")
    public User updateUser(@RequestBody User user ){
        return userService.updateUser(user);
    }

    @DeleteMapping("deleteUser")
    public void delete(@RequestBody User user){

        userService.deleteUser(user);
    }











}
