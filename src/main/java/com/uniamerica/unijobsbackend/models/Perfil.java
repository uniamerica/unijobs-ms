package com.uniamerica.unijobsbackend.models;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Entity
@Data
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String imagem;

    @Column
    private String nome;

    @Column
    private String ra;

    @Column
    private String telefone;

    @Column
    private String email;

    @Column
    private String senha;

}
