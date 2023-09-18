package com.hiberus.modelos;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pizzas")
@Entity
@Getter
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Setter
    @Column(name = "nombre")
    private String nombre;
}
