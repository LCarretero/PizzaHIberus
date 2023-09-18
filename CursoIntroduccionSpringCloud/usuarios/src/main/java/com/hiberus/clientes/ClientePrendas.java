package com.hiberus.clientes;

import com.hiberus.dto.PrendaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "prendas")
public interface ClientePrendas {
    @GetMapping(value = "/prendas/obtenerPrendasPorUsuario")
    ResponseEntity<List<PrendaDto>> obtenerPrendasPorUsuario(@RequestParam Integer idUsuario);
}
