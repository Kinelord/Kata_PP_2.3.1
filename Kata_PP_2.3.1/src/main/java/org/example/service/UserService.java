package org.example.service;

import org.example.models.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUser(Long id);

    void addUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    void deleteAllUser();
}
