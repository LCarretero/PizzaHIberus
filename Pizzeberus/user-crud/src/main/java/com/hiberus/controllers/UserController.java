package com.hiberus.controllers;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.dto.UserDTO;
import com.hiberus.exceptions.UserBadRequestException;
import com.hiberus.exceptions.UserUnauthorizedException;
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
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(User user) {
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (UserBadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestHeader String auth) {
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
}
