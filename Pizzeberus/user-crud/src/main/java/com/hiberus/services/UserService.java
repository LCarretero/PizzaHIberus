package com.hiberus.services;

import com.hiberus.dto.UserDTO;
import com.hiberus.exceptions.UserBadRequestException;
import com.hiberus.exceptions.UserNotFoundException;
import com.hiberus.exceptions.UserUnauthorizedException;
import com.hiberus.models.UpdatePizza;
import com.hiberus.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDTO getUser(UUID userId) throws UserNotFoundException;

    List<User> getAllUsers(String auth) throws UserUnauthorizedException;

    UserDTO createUser(User user) throws UserBadRequestException;

    UserDTO updateUser(User user) throws UserNotFoundException;

    User deleteUser(UUID userId) throws UserNotFoundException;

    UserDTO addPizza(UpdatePizza updatePizza) throws UserNotFoundException;

    UserDTO deletePizza(UpdatePizza updatePizza) throws UserNotFoundException;

}
