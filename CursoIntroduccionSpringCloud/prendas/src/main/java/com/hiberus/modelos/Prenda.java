package com.hiberus.modelos;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prendas")
@Entity
@Getter
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Setter
    @Column(name = "nombre")
    private String nombre;
    @Setter
    @Column(name = "talla")
    private String talla;
    @Setter
    @Column(name = "color")
    private String color;
    @Column(name = "idUsuario")
    private Integer idUsuario;

}
