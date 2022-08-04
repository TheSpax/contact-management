package com.example.contactsproject.service;

import com.example.contactsproject.entity.Users;

import java.util.List;

public interface UsersService {

    List<Users> getAllUsers();

    Users getUserById(Long id);

    Users saveUser(Users user);

    Users updateUser(Users user);

    void deleteUserById(Long id);

}