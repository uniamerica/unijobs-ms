package com.uniamerica.unijobsbackend.models;

import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class TipoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipo_produto;

    private String nome;

    private String descricao;

    public TipoProduto() {
    }

    public TipoProduto(Integer id_tipo_produto) {
        this.id_tipo_produto = id_tipo_produto;
    }

    public TipoProduto(Integer id_tipo_produto, String nome, String descricao) {
        this.id_tipo_produto = id_tipo_produto;
        this.nome = nome;
        this.descricao = descricao;
    }
}
