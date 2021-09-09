package com.uniamerica.unijobsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class TipoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipo_produto;

    private String nome;

    private String descricao;

    public Integer getId_tipo_produto() {return id_tipo_produto;}
    public void setId_tipo_produto(Integer id_tipo_produto) {this.id_tipo_produto = id_tipo_produto;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

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
