package com.uniamerica.unijobsbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.models.TipoProduto;
import com.uniamerica.unijobsbackend.models.Usuario;
import lombok.Data;


@Data
public class ProdutoDTO {
    private String titulo;
    private String descricao;
    private Double preco;
    private String miniatura;
    private Integer prazo;
    @JsonProperty("anunciante")
    private String usuarioNome;
    @JsonProperty("contato")
    private String usuarioCelular;
    private TipoProduto tipoProduto;

//    public ProdutoDTO(Produto p) {
//        this.titulo = p.getTitulo();
//        this.descricao = p.getDescricao();
//        this.preco = p.getPreco();
//        this.miniatura = p.getMiniatura();
//        this.prazo = p.getPrazo();
//        this.usuario = new UsuarioDto(p.getUsuario());
//        this.tipoProduto = p.getTipoProduto();
//    }
}
