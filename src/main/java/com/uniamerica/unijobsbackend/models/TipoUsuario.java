package com.uniamerica.unijobsbackend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

//    @OneToMany
//    private List<Usuario>usuarios;

}
