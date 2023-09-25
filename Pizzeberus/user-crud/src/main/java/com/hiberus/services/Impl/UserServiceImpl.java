package com.hiberus.services.Impl;


import com.hiberus.clients.ClientsPizzas;
import com.hiberus.dto.PizzaDTO;
import com.hiberus.dto.UserDTO;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.exceptions.UserBadRequestException;
import com.hiberus.exceptions.UserNotFoundException;
import com.hiberus.exceptions.UserUnauthorizedException;
import com.hiberus.mappers.UserMapper;
import com.hiberus.models.UpdatePizza;
import com.hiberus.models.User;
import com.hiberus.repositories.UserRepository;
import com.hiberus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientsPizzas clientsPizzas;

    @Value(value = "${KEYPASS}")
    private String KEYPASS;

    @Override
    public UserDTO getUser(UUID userId) throws UserNotFoundException {
        return UserMapper.INSTANCE.mapToDTO(obtainUser(userId));
    }

    @Override
    public List<User> getAllUsers(String auth) throws UserUnauthorizedException {
        if (!KEYPASS.equals(auth)) throw new UserUnauthorizedException();
        return userRepository.findAll();
    }

    @Override
    public UserDTO createUser(User user) throws UserBadRequestException {
        if (user.getName().isBlank() || user.getName().isEmpty()) throw new UserBadRequestException();
        User userToDB = User.builder()
                .id(UUID.randomUUID())
                .favouritesPizzas(new HashSet<>())
                .name(user.getName())
                .build();
        return UserMapper.INSTANCE.mapToDTO(userRepository.save(userToDB));
    }

    @Override
    public UserDTO updateUser(String id, User user) throws UserNotFoundException {
        User userFromDB = obtainUser(UUID.fromString(id));
        User userForDb = User.builder().id(userFromDB.getId()).name(user.getName()).favouritesPizzas(user.getFavouritesPizzas()).build();
        return UserMapper.INSTANCE.mapToDTO(saveUser(userForDb));
    }

    @Override
    public User deleteUser(UUID userId) throws UserNotFoundException {
        User userDB = obtainUser(userId);
        userRepository.delete(userDB);
        return userDB;
    }

    @Override
    public UserDTO addPizza(UpdatePizza updatePizza) throws UserNotFoundException, PizzaNotFoundException {
        User userDB = obtainUser(updatePizza.getUserId());
        try {
            clientsPizzas.getPizza(updatePizza.getPizzaId());
        } catch (Exception e) {
            throw new PizzaNotFoundException();
        }
        Set<UUID> favouritesPizzas = userDB.getFavouritesPizzas();
        favouritesPizzas.add(updatePizza.getPizzaId());
        List<PizzaDTO> userPizzas = (clientsPizzas.obtainFavouritesPizzas(new ArrayList<>(favouritesPizzas))).getBody();
        saveUser(userDB);
        return new UserDTO(userPizzas, userDB.getName());
    }

    @Override
    public UserDTO deletePizza(UpdatePizza updatePizza) throws UserNotFoundException {
        User userDB = obtainUser(updatePizza.getUserId());
        Set<UUID> favouritesPizzas = userDB.getFavouritesPizzas();
        favouritesPizzas.remove(updatePizza.getPizzaId());
        List<PizzaDTO> userPizzas = (clientsPizzas.obtainFavouritesPizzas(new ArrayList<>(favouritesPizzas))).getBody();
        saveUser(userDB);
        return new UserDTO(userPizzas, userDB.getName());
    }

    @Override
    public List<PizzaDTO> getFavourites(UUID userId) throws UserNotFoundException {
        User userDb = obtainUser(userId);
        Set<UUID> favouritesPizzas = userDb.getFavouritesPizzas();
        return (clientsPizzas.obtainFavouritesPizzas(new ArrayList<>(favouritesPizzas))).getBody();
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
