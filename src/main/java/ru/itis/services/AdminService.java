package ru.itis.services;

import ru.itis.models.User;

import java.util.List;


public interface AdminService {
    List<User> getAllUsers();

    void createTempPassword(Long userId);
}
