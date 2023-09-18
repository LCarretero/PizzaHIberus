package com.hiberus.servicios;

import com.hiberus.modelos.Prenda;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicioPrendas {

    List<Prenda> obtenerPrendas();

    List<Prenda> obtenerPrendasPorIdUsuario(Integer idUsuario);
}
