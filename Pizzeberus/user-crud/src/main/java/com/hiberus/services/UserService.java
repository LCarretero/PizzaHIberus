package com.hiberus.services;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.dto.UserDTO;
import com.hiberus.exceptions.*;
import com.hiberus.models.UpdatePizza;
import com.hiberus.models.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserService {
    User getUser(UUID userId) throws UserNotFoundException;

    List<User> getAllUsers(String auth) throws UserUnauthorizedException;

    UserDTO createUser(User user) throws UserBadRequestException;

    UserDTO updateUser(String id, User user) throws UserNotFoundException, PizzaNotFoundException, UserBadRequestException;

    User deleteUser(UUID userId) throws UserNotFoundException;

    UserDTO addPizza(UpdatePizza updatePizza) throws UserNotFoundException, PizzaNotFoundException;

    UserDTO deletePizza(UpdatePizza updatePizza) throws UserNotFoundException, PizzaBadRequestException, PizzaNotFoundException;

    Set<PizzaDTO> getFavourites(UUID userId) throws UserNotFoundException;
}
