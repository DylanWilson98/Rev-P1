package com.dylan.service;

import com.dylan.model.Game;
import com.dylan.model.User;
import com.dylan.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public UserService(UserRepo userRepo){

        this.userRepo = userRepo;
    }




    public List<User> getAllUsers() {

        return userRepo.getAllUsers();
    }

    public User getUser(Integer user_id) {

        return userRepo.getUserById(user_id);
    }



    public User addUser(User user) {

        List<User> userList = userRepo.getAllUsers();
        boolean uniqueUsername = true;
        for (User value : userList) {
            if (user.getUsername().equals(value.getUsername())) {

                uniqueUsername = false;

                break;
            }

        }

        if (uniqueUsername){




            return userRepo.add(user);


        }else {
            System.out.println("Username is not unique please enter a unique username.");
// TODO: 3/4/2022 add logging here!!!! 
            // TODO: 3/3/2022 More logic needed here.


        }
        return null;

    }

    public void deleteUser(User user) {

        userRepo.deleteUser(user);
    }


    public User updateUser(User user) {

        userRepo.update(user);
        return user;
    }







    
//    public void addUserGame(int user_id, int game_id){
//        // TODO: 3/5/2022 check logic here and figure out how to formulate controller as well as how to format in postman*********************
//        userRepo.addGame(user_id, game_id);
//
//
//    }





}
