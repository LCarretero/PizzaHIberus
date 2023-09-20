package com.hiberus.services.Impl;


import com.hiberus.models.User;
import com.hiberus.repositories.UserRepository;
import com.hiberus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
     @Autowired
     UserRepository repositorioUsuario;
    public List<User> obtenerUsuarios(){
        return repositorioUsuario.findAll();
    }
}
