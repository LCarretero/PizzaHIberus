package com.hiberus.servicios;

import com.hiberus.dto.PizzaDto;
import java.util.List;

public interface ServicioPizzas {
    List<PizzaDto> obtenerPizzasPorUsuario(Integer idUsuario);
}
