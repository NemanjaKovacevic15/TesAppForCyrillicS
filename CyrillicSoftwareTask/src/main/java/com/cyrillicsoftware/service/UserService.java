package com.cyrillicsoftware.service;

import java.util.List;

import com.cyrillicsoftware.model.User;

public interface UserService {

    User findOne(Long id);

    List<User> findAll();

    User save(User toSave);

    User delete(Long id);

    User findByName(String name);
}
