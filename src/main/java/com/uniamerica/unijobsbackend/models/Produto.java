package com.uniamerica.unijobsbackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
public class Produto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_produto;

    private String titulo;

    private String descricao;

    private Double preco;

    private String miniatura;

    private Boolean ativo;

    private Integer prazo;

    @ManyToOne
    @JoinColumn(name = "id_tipo_produto")
    private TipoProduto tipoProduto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Produto() {}

    public Produto(Integer id_produto, String titulo, String descricao, Double preco, String miniatura, Boolean ativo, Integer prazo, TipoProduto tipoProduto, Usuario usuario) {
        this.id_produto = id_produto;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.miniatura = miniatura;
        this.ativo = ativo;
        this.prazo = prazo;
        this.tipoProduto = tipoProduto;
        this.usuario = usuario;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(String miniatura) {
        this.miniatura = miniatura;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
