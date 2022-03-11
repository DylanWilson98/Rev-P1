package com.dylan.service;

import com.dylan.model.User;
import com.dylan.repository.GameRepo;
import com.dylan.repository.UserRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    UserRepo userRepo;
            public UserService(UserRepo userRepo){

        this.userRepo = userRepo;
            }


            public List<User> findAllUsers(){

                return userRepo.findAll();
            }

            public User getUserById(int user_id) {

                return userRepo.getById(user_id);
            }

            public User addUser(User user){

                List<User> userList = userRepo.findAll();
                boolean uniqueUsername = true;
                for (User value : userList) {
                    if (user.getUsername().equals(value.getUsername())) {

                        uniqueUsername = false;

                        break;
                    }

                }

                if (uniqueUsername){




                    return userRepo.save(user);


                }else {
                    System.out.println("Username is not unique please enter a unique username.");
// TODO: 3/4/2022 add logging here!!!!
                    // TODO: 3/3/2022 More logic needed here.


                }
                return null;
            }

            public User updateUser(User user){

                return userRepo.save(user);
            }

            public void deleteUser(User user){

                 userRepo.delete(user);
            }




}
