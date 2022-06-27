package com.example.bookstore.service;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.UserRegistrationDTO;
import com.example.bookstore.entity.UserRegistrationData;

import java.util.List;
import java.util.Optional;

public interface IUserRegistrationService {
    String createUser(UserRegistrationDTO userRegistrationDTO);
    List<UserRegistrationData> getAllUsers(String token);
    UserRegistrationData getUserById(int id);
    List<UserRegistrationData> getUserByEmail(String token);
    UserRegistrationData updateUser(String token,UserRegistrationDTO userRegistrationDTO);
    Optional<UserRegistrationData> login(LoginDTO loginDTO);

    String getToken(String email);

    List<UserRegistrationData> getAllUserDataByToken(String token);
}
