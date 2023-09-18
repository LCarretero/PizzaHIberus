package com.hiberus.servicios;

import com.hiberus.modelos.Pizza;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicioPrendas {

    List<Pizza> obtenerPrendas();

    List<Pizza> obtenerPrendasPorIdUsuario(Integer idUsuario);
}
