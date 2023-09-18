package com.hiberus.servicios;

import com.hiberus.dto.PrendaDto;
import java.util.List;

public interface ServicioPrendas {
    List<PrendaDto> obtenerPrendasPorUsuario(Integer idUsuario);
}
