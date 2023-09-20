package com.hiberus.services;

import com.hiberus.dto.UserDTO;
import com.hiberus.exceptions.UserBadRequestException;
import com.hiberus.exceptions.UserNotFoundException;
import com.hiberus.exceptions.UserUnauthorizedException;
import com.hiberus.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDTO getUser(UUID user) throws UserNotFoundException;

    List<UserDTO> getAllUsers(String auth) throws UserUnauthorizedException;

    UserDTO createUser(User user) throws UserBadRequestException;

    UserDTO updateUser(User userId) throws UserNotFoundException;

    void deleteUser(UUID userId) throws UserNotFoundException;

    UserDTO addPizza(UUID userId, UUID pizzaId) throws UserNotFoundException;

    UserDTO deletePizza(UUID userId, UUID pizzaId) throws UserNotFoundException;

}
