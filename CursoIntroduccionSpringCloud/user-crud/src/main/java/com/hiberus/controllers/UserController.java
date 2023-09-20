package com.hiberus.controllers;

import com.hiberus.services.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    ServicioUsuarios servicioUsuarios;


//    @GetMapping(value = "/obtenerUsuarios")
//    public ResponseEntity <List<UserDTO>> obtenerUsuarios(){
//        return servicioUsuarios.obtenerUsuarios().stream().map(UserMapper.INSTANCE::mapToDTO).collect(Collectors.toList());
//    }

//    @GetMapping(value = "/obtenerPizzasUsuario")
//    public ResponseEntity <List<PizzaDto>> obtenerPizzasUsuario(@RequestParam Integer idUsuario){
//        List<PizzaDto> listaPizzasUsuario = servicioPizzas.obtenerPizzasPorUsuario(idUsuario);
//        return new ResponseEntity<>(listaPizzasUsuario,HttpStatus.OK);
//    }
}
