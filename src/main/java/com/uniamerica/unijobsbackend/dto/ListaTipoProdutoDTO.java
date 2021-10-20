package com.uniamerica.unijobsbackend.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListaTipoProdutoDTO implements Serializable {
    private Integer id_tipo_produto;
    private String nome;
    private String descricao;
}
