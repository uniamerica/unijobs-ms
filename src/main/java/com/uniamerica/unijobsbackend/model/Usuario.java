package com.uniamerica.unijobsbackend.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String senha;

    @Column(nullable = false)
    private String nome;
    private String celular;
    private  String ra;
}
