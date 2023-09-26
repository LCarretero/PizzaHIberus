package com.hiberus.controllers;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.dto.UserDTO;
import com.hiberus.exceptions.PizzaNotFoundException;
import com.hiberus.exceptions.UserBadRequestException;
import com.hiberus.exceptions.UserNotFoundException;
import com.hiberus.exceptions.UserUnauthorizedException;
import com.hiberus.models.UpdatePizza;
import com.hiberus.models.User;
import com.hiberus.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
@Api(tags = "User-crud")
public class UserController {

    private final UserService userService;

    @PostMapping()
    @ApiResponses(value = {@ApiResponse(code = 201, message = "User created successfully")})
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        try {
            UserDTO userToDb = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userToDb);
        } catch (UserBadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable() UUID id) {
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
    public ResponseEntity<Set<PizzaDTO>> favourites(@RequestParam UUID idUsuario) {
        try {
            return ResponseEntity.ok(userService.getFavourites(idUsuario));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, user));
        } catch (UserNotFoundException | PizzaNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UserBadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/add")
    public ResponseEntity<UserDTO> addPizza(@RequestBody UpdatePizza updatePizza) {
        try {
            return ResponseEntity.ok(userService.addPizza(updatePizza));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (PizzaNotFoundException e) {
            return ResponseEntity.badRequest().build();
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
