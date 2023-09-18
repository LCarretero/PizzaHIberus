package com.hiberus.servicios.Impl;

import com.hiberus.modelos.Prenda;
import com.hiberus.repositorios.RepositorioPrenda;
import com.hiberus.servicios.ServicioPrendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPrendasImpl implements ServicioPrendas {

    @Autowired
    RepositorioPrenda repositorioPrenda;

    @Override
    public List<Prenda> obtenerPrendas() {
        return repositorioPrenda.findAll();
    }

    @Override
    public List<Prenda> obtenerPrendasPorIdUsuario(Integer idUsuario) {
        return repositorioPrenda.findByIdUsuario(idUsuario);
    }
}
