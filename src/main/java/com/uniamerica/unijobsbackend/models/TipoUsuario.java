package com.uniamerica.unijobsbackend.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@RequiredArgsConstructor
@Entity
@Data
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    public TipoUsuario(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TipoUsuario(String nome) {
        this.nome = nome;
    }

//    @OneToMany
//    private List<Usuario>usuarios;

}
