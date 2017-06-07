package com.empresa.service;

import com.empresa.entities.User;

import java.util.List;

/**
 * Created by cyborg on 6/3/17.
 */
public interface UserService {

    User findById(Long id);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);

    void deleteAllUsers();

    List<User> findAllUsers();

    boolean isUserExist(User user);
}
