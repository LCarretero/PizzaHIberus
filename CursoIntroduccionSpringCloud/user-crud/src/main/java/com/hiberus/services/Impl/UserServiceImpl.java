package com.hiberus.services.Impl;


import com.hiberus.dto.UserDTO;
import com.hiberus.exceptions.UserBadRequestException;
import com.hiberus.exceptions.UserNotFoundException;
import com.hiberus.exceptions.UserUnauthorizedException;
import com.hiberus.mappers.UserMapper;
import com.hiberus.models.User;
import com.hiberus.repositories.UserRepository;
import com.hiberus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Value(value = "${KEYPASS}")
    private String KEYPASS;

    @Override
    public UserDTO getUser(UUID userId) throws UserNotFoundException {
        return UserMapper.INSTANCE.mapToDTO(obtainUser(userId));
    }

    @Override
    public List<UserDTO> getAllUsers(String auth) throws UserUnauthorizedException {
        if (!KEYPASS.equals(auth)) throw new UserUnauthorizedException();
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(User user) throws UserBadRequestException {
        if (user.getName().isBlank() || user.getName().isEmpty()) throw new UserBadRequestException();
        return UserMapper.INSTANCE.mapToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(User user) throws UserNotFoundException {
        User userFromDB = obtainUser(user.getId());
        User userForDb = User.builder().id(userFromDB.getId()).name(user.getName()).favouritesPizzas(user.getFavouritesPizzas()).build();
        return UserMapper.INSTANCE.mapToDTO(saveUser(userForDb));
    }

    @Override
    public void deleteUser(UUID userId) throws UserNotFoundException {
        User userDB = obtainUser(userId);
        userRepository.delete(userDB);
    }

    @Override
    public UserDTO addPizza(UUID userId, UUID pizzaId) throws UserNotFoundException {
        User userDB = obtainUser(userId);
        userDB.getFavouritesPizzas().add(pizzaId);
        return UserMapper.INSTANCE.mapToDTO(saveUser(userDB));
    }

    @Override
    public UserDTO deletePizza(UUID userId, UUID pizzaId) throws UserNotFoundException {
        User userDb = obtainUser(userId);
        if (userDb.getFavouritesPizzas().remove(pizzaId)) return UserMapper.INSTANCE.mapToDTO(saveUser(userDb));
        return UserMapper.INSTANCE.mapToDTO(userDb);
    }

    //region PRIVATE_METHODS
    private User obtainUser(UUID userId) throws UserNotFoundException {
        User userDB = userRepository.findById(userId).orElse(null);
        if (userDB == null) throw new UserNotFoundException();
        return userDB;
    }

    private User saveUser(User user) {
        return userRepository.save(user);
    }
    //endregion
}
