package com.example.contactsproject.service;

import com.example.contactsproject.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Integer id);

    User saveUser(User user);

    User updateUser(User user);

    void deleteUserById(Integer id);

}
