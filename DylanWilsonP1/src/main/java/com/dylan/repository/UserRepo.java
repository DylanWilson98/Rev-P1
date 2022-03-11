package com.dylan.repository;

import com.dylan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {

@Query("from User where user_id = :user_id")
    User getById(@Param("user_id") int user_id);





}
