package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.entity.Users;
import com.example.contactsproject.repository.UsersRepository;
import com.example.contactsproject.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements GenericService<Users> {

    private final UsersRepository usersRepository;

    @Override
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users getByUid(UUID uid) {
        return usersRepository.findByUid(uid);
    }

    @Override
    public Users save(Users user) {
        user.setUid(UUID.randomUUID());
        return usersRepository.save(user);
    }

    @Override
    public Users update(Users user) {
        return usersRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteByUid(UUID uid) {
        usersRepository.deleteByUid(uid);
    }
}