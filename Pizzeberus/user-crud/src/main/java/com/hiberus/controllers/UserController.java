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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
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
    @ApiOperation(value = "Create a user", response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully"),
            @ApiResponse(code = 400, message = "The user name provided is not valid")
    })
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        try {
            UserDTO userToDb = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userToDb);
        } catch (UserBadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get a user by ID", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<User> getUser(@PathVariable() UUID id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/all")
    @ApiOperation(value = "Get all users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users found"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader(name = "Auth") String auth) {
        try {
            return ResponseEntity.ok(userService.getAllUsers(auth));
        } catch (UserUnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping
    @ApiOperation(value = "Get a pizza by name", response = UUID.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pizza found by name"),
            @ApiResponse(code = 404, message = "Pizza not found by name")
    })
    public ResponseEntity<Set<PizzaDTO>> favourites(@RequestParam UUID idUsuario) {
        try {
            return ResponseEntity.ok(userService.getFavourites(idUsuario));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update a user by ID", response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User updated successfully"),
            @ApiResponse(code = 404, message = "User or pizza not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
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
    @ApiOperation(value = "Add a pizza to a user's favorites", response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pizza added successfully"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Pizza not found")
    })
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
    @ApiOperation(value = "Delete a pizza from a user's favorites", response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pizza deleted from favourites successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<UserDTO> deletePizza(@RequestBody UpdatePizza updatePizza) {
        try {
            return ResponseEntity.ok(userService.deletePizza(updatePizza));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete a user by ID", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User deleted successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<User> deleteUser(@PathVariable(name = "id") UUID uuid) {
        try {
            return ResponseEntity.ok(userService.deleteUser(uuid));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
