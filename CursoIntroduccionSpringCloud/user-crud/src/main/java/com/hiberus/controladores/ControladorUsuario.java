package com.hiberus.controladores;

import com.hiberus.dto.PizzaDto;
import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;
import com.hiberus.servicios.ServicioPizzas;
import com.hiberus.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class ControladorUsuario {

    @Autowired
    ServicioUsuarios servicioUsuarios;

    @Autowired
    ServicioPizzas servicioPizzas;

    @GetMapping(value = "/obtenerUsuarios")
    public ResponseEntity <List<UsuarioDto>> obtenerUsuarios(){
        List<Usuario> listaUsuarios = servicioUsuarios.obtenerUsuarios();
        List<UsuarioDto> listaUsuariosDto = new ArrayList<>();
        for(Usuario usuario: listaUsuarios){
            UsuarioDto usuarioDto = UsuarioDto.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .apellidos(usuario.getApellidos())
                    .email(usuario.getEmail())
                    .build();
            listaUsuariosDto.add(usuarioDto);
        }
            return new ResponseEntity<>(listaUsuariosDto, HttpStatus.OK);
    }

    @GetMapping(value = "/obtenerPizzasUsuario")
    public ResponseEntity <List<PizzaDto>> obtenerPizzasUsuario(@RequestParam Integer idUsuario){
        List<PizzaDto> listaPizzasUsuario = servicioPizzas.obtenerPizzasPorUsuario(idUsuario);
        return new ResponseEntity<>(listaPizzasUsuario,HttpStatus.OK);
    }
}
