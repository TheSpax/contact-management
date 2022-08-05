package com.example.contactsproject.service;

import java.util.List;
import java.util.UUID;

public interface GenericService<T> {

    List<T> getAll();

    T getByUid(UUID uid);

    T save(T t);

    T update(T t);

    void deleteByUid(UUID uid);

}
