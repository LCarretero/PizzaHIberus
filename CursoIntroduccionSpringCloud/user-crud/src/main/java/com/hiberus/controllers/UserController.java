package com.hiberus.controllers;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.dto.UserDTO;
import com.hiberus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping(value = "/favourites")
    public ResponseEntity<List<PizzaDTO>> favourites(@RequestParam Integer idUsuario) {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
