package com.uniamerica.unijobsbackend.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tipos_servico")
public class TipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipoServico;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A Descrição é obrigatória")
    private String descricao;

    @OneToMany(mappedBy = "tipoServico")
    private List<Servico> servico = new ArrayList<>();

    public TipoServico(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public TipoServico() {

    }
}
