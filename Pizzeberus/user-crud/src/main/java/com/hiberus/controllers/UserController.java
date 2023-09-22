package com.hiberus.controllers;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.dto.UserDTO;
import com.hiberus.exceptions.UserBadRequestException;
import com.hiberus.exceptions.UserNotFoundException;
import com.hiberus.exceptions.UserUnauthorizedException;
import com.hiberus.models.UpdatePizza;
import com.hiberus.models.User;
import com.hiberus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        try {
            UserDTO result = userService.createUser(user);
            return ResponseEntity.ok(result);
        } catch (UserBadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable() UUID id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader(name = "Auth") String auth) {
        try {
            return ResponseEntity.ok(userService.getAllUsers(auth));
        } catch (UserUnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping(value = "/favourites")
    public ResponseEntity<List<PizzaDTO>> favourites(@RequestParam UUID idUsuario) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(user));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/add")
    public ResponseEntity<UserDTO> addPizza(@RequestBody UpdatePizza updatePizza) {
        try {
            return ResponseEntity.ok(userService.addPizza(updatePizza));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/delete")
    public ResponseEntity<UserDTO> deletePizza(@RequestBody UpdatePizza updatePizza) {
        try {
            return ResponseEntity.ok(userService.deletePizza(updatePizza));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(name = "id") UUID uuid) {
        try {
            return ResponseEntity.ok(userService.deleteUser(uuid));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

}
