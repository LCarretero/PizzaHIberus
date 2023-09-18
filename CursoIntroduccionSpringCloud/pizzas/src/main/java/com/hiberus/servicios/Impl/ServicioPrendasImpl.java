package com.hiberus.servicios.Impl;

import com.hiberus.modelos.Pizza;
import com.hiberus.repositorios.RepositorioPizza;
import com.hiberus.servicios.ServicioPrendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPrendasImpl implements ServicioPrendas {

    @Autowired
    RepositorioPizza repositorioPizza;

    @Override
    public List<Pizza> obtenerPrendas() {
        return repositorioPizza.findAll();
    }

    @Override
    public List<Pizza> obtenerPrendasPorIdUsuario(Integer idUsuario) {
        return repositorioPizza.findByIdUsuario(idUsuario);
    }
}
