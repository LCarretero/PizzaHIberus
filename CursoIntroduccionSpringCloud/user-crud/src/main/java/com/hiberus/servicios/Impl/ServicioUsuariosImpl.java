package com.hiberus.servicios.Impl;

import com.hiberus.modelos.Usuario;
import com.hiberus.repositorios.RepositorioUsuario;
import com.hiberus.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuariosImpl implements ServicioUsuarios {
     @Autowired
    RepositorioUsuario repositorioUsuario;
    public List<Usuario> obtenerUsuarios(){
        return repositorioUsuario.findAll();
    }
}
