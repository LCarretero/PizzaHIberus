package com.hiberus.controllers;

import com.hiberus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;


//    @GetMapping(value = "/obtenerUsuarios")
//    public ResponseEntity <List<UserDTO>> obtenerUsuarios(){
//        return userService.obtenerUsuarios().stream().map(UserMapper.INSTANCE::mapToDTO).collect(Collectors.toList());
//    }

//    @GetMapping(value = "/obtenerPizzasUsuario")
//    public ResponseEntity <List<PizzaDto>> obtenerPizzasUsuario(@RequestParam Integer idUsuario){
//        List<PizzaDto> listaPizzasUsuario = servicioPizzas.obtenerPizzasPorUsuario(idUsuario);
//        return new ResponseEntity<>(listaPizzasUsuario,HttpStatus.OK);
//    }
}
