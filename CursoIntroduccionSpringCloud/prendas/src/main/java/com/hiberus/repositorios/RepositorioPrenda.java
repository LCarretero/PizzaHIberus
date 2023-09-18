package com.hiberus.repositorios;

import com.hiberus.modelos.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioPrenda extends JpaRepository<Prenda,Integer> {
    List<Prenda> findByIdUsuario(Integer idUsuario);
}
