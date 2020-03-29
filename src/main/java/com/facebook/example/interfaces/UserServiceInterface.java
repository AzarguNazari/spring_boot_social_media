package com.facebook.example.interfaces;

import com.facebook.example.models.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {

    void saveUser(User user);

    void deleteUser(Long userID);

    Optional<User> getUser(Long userID);

    List<User> getUsers();
}
