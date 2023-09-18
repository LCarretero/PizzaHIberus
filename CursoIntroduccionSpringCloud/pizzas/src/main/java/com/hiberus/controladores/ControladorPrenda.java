package com.hiberus.controladores;

import com.hiberus.dto.PrendaDto;
import com.hiberus.modelos.Pizza;
import com.hiberus.servicios.ServicioPrendas;
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
@RequestMapping(value = "/prendas")
public class ControladorPrenda {

    @Autowired
    ServicioPrendas servicioPrendas;

    @GetMapping(value = "/obtenerPrendas")
    ResponseEntity<List<PrendaDto>> obtenerPrendas(){
        List<Pizza> listaPizzas = servicioPrendas.obtenerPrendas();
        List<PrendaDto> listaPrendasDto = new ArrayList<>();
        for(Pizza pizza : listaPizzas){
            PrendaDto prendaDto = PrendaDto.builder()
                    .id(pizza.getId())
                    .nombre(pizza.getNombre())
                    .talla(pizza.getTalla())
                    .color(pizza.getColor())
                    .idUsuario(pizza.getIdUsuario())
                    .build();
            listaPrendasDto.add(prendaDto);
        }
        return new ResponseEntity<>(listaPrendasDto, HttpStatus.OK);
    }

    @GetMapping(value = "/obtenerPrendasPorUsuario")
    ResponseEntity<List<PrendaDto>> obtenerPrendasPorUsuario(@RequestParam Integer idUsuario){
        List<Pizza> listaPizzas = servicioPrendas.obtenerPrendasPorIdUsuario(idUsuario);
        List<PrendaDto> listaPrendasDto = new ArrayList<>();
        for(Pizza pizza : listaPizzas){
            PrendaDto prendaDto = PrendaDto.builder()
                    .id(pizza.getId())
                    .nombre(pizza.getNombre())
                    .talla(pizza.getTalla())
                    .color(pizza.getColor())
                    .idUsuario(pizza.getIdUsuario())
                    .build();
            listaPrendasDto.add(prendaDto);
        }
        return new ResponseEntity<>(listaPrendasDto, HttpStatus.OK);
    }

}
