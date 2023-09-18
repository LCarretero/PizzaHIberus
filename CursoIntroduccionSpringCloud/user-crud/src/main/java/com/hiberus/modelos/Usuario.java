package com.hiberus.modelos;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Entity
@Getter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Setter
    @Column(name = "nombre")
    private String nombre;
    @ElementCollection()
    private List<Long> pizzasFav;

}