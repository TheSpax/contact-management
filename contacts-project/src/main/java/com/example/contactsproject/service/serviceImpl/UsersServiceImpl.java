package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.entity.Users;
import com.example.contactsproject.repository.UsersRepository;
import com.example.contactsproject.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUserById(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User doesn't exist"));
    }

    @Override
    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public Users updateUser(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        usersRepository.deleteById(id);
    }
}