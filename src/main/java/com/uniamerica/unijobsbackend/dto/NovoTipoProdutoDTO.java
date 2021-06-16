package com.uniamerica.unijobsbackend.dto;

import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.models.TipoProduto;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NovoTipoProdutoDTO {
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    public TipoProduto converteModelo(){
        TipoProduto tipoProduto = new TipoProduto();
        tipoProduto.setNome(nome);
        tipoProduto.setDescricao(descricao);
        return tipoProduto;
    }
}
