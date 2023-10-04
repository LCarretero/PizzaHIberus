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
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ClientsPizzas clientsPizzas;
    private final String KEYPASS;

    @Override
    public User getUser(UUID userId) throws UserNotFoundException {
        return obtainUser(userId);
    }

    @Override
    public List<User> getAllUsers(String auth) throws UserUnauthorizedException {
        if (!KEYPASS.equals(auth)) throw new UserUnauthorizedException();
        return userRepository.findAll();
    }

    @Override
    public UserDTO createUser(User user) throws UserBadRequestException {
        if (!isNameValid(user.getName())) throw new UserBadRequestException();
        User userToDB = User.builder().id(UUID.randomUUID()).favouritesPizzas(new HashSet<>()).name(user.getName()).build();
        return UserMapper.INSTANCE.mapToDTO(userRepository.save(userToDB));
    }

    @Override
    public UserDTO updateUser(String id, User user) throws UserNotFoundException, PizzaNotFoundException, UserBadRequestException {
        User userFromDB = obtainUser(UUID.fromString(id));
        if (!isNameValid(user.getName()))
            throw new UserBadRequestException();
        Set<PizzaDTO> pizzaDTOSet;
        try {
            pizzaDTOSet = clientsPizzas.obtainFavouritesPizzas(user.getFavouritesPizzas()).getBody();
        } catch (Exception e) {
            throw new PizzaNotFoundException();
        }
        User userForDb = User.builder().id(userFromDB.getId())
                .name(user.getName())
                .favouritesPizzas(user.getFavouritesPizzas())
                .build();

        return new UserDTO(pizzaDTOSet, saveUser(userForDb).getName());
    }

    @Override
    public User deleteUser(UUID userId) throws UserNotFoundException {
        User userDB = obtainUser(userId);
        userRepository.delete(userDB);
        return userDB;
    }

    @Override
    @CircuitBreaker(name = "pizzaService", fallbackMethod = "fallbackPizzaService")
    public UserDTO addPizza(UpdatePizza updatePizza) throws UserNotFoundException, PizzaNotFoundException {
        User userDB = obtainUser(updatePizza.getUserId());
        String pizzaName = updatePizza.getPizzaIdOrName();
        UUID id = isPizzaValid(pizzaName);
        Set<UUID> favouritesPizzas = userDB.getFavouritesPizzas();

        favouritesPizzas.add(isUUID(pizzaName) ? UUID.fromString(pizzaName) : id);
        Set<PizzaDTO> userPizzas = clientsPizzas.obtainFavouritesPizzas(favouritesPizzas).getBody();
        saveUser(userDB);
        return new UserDTO(userPizzas, userDB.getName());
    }

    @Override
    @CircuitBreaker(name = "pizzaService", fallbackMethod = "fallbackPizzaService")
    public UserDTO deletePizza(UpdatePizza updatePizza) throws UserNotFoundException, PizzaNotFoundException {
        User userDB = obtainUser(updatePizza.getUserId());
        String pizzaName = updatePizza.getPizzaIdOrName();
        UUID id = isPizzaValid(pizzaName);
        Set<UUID> favouritesPizzas = userDB.getFavouritesPizzas();

        favouritesPizzas.remove(isUUID(pizzaName) ? UUID.fromString(pizzaName) : id);
        Set<PizzaDTO> userPizzas = clientsPizzas.obtainFavouritesPizzas(favouritesPizzas).getBody();
        saveUser(userDB);
        return new UserDTO(userPizzas, userDB.getName());
    }

    @Override
    public Set<PizzaDTO> getFavourites(UUID userId) throws UserNotFoundException {
        User userDb = obtainUser(userId);
        Set<UUID> favouritesPizzas = userDb.getFavouritesPizzas();
        return clientsPizzas.obtainFavouritesPizzas(favouritesPizzas).getBody();
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

    private boolean isNameValid(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ]+$");
    }

    public boolean isUUID(String uuidStr) {
        return uuidStr != null && uuidStr.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
    }

    private UUID isPizzaValid(String pizza) throws PizzaNotFoundException {
        if (isUUID(pizza)) {
            try {
                clientsPizzas.getPizza(UUID.fromString(pizza)).getBody();
            } catch (Exception e) {
                throw new PizzaNotFoundException();
            }
        } else {
            try {
                return clientsPizzas.getPizzaByName(pizza).getBody();
            } catch (Exception e) {
                throw new PizzaNotFoundException();
            }
        }
        return null;
    }

    private UserDTO fallbackPizzaService(UpdatePizza updatePizza, Throwable throwable) {
        return new UserDTO(Collections.emptySet(), "The pizza service is unavailable");
    }
    //endregion
}
