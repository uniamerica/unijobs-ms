package com.uniamerica.unijobsbackend.dto;

import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.models.Servico;
import lombok.Data;

import java.io.Serializable;

@Data
public class ItensDTO implements Serializable {
    private Integer id;
    private String titulo;
    private String descricao;
    private Double preco;
    private String miniatura;
    private boolean ativo;
    private Integer prazo;

    private TipoItemDTO tipo;

    public ItensDTO(Servico servico) {
        id = servico.getId_servico();
        titulo = servico.getTitulo();
        descricao = servico.getDescricao();
        preco = servico.getPreco();
        miniatura = servico.getMiniatura();
        ativo = servico.isAtivo();
        prazo = servico.getPrazo();
        tipo = new TipoItemDTO(servico.getTipoServico());
    }

    public ItensDTO(Produto produto) {
        id = produto.getId_produto();
        titulo = produto.getTitulo();
        descricao = produto.getDescricao();
        preco = produto.getPreco();
        miniatura = produto.getMiniatura();
        ativo = produto.getAtivo();
        prazo = produto.getPrazo();
        tipo = new TipoItemDTO(produto.getTipoProduto());
    }
}