package com.uniamerica.unijobsbackend.dto.input;

import com.uniamerica.unijobsbackend.models.Produto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NovoProdutoDTO {
    @NotBlank(message = "O Título é obrigatório.")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "O preço é obrigatório.")
    private Double preco;

    @NotBlank(message = "A miniatura é obrigatória.")
    private String miniatura;

    @NotNull(message = "O prazo é obrigatório.")
    private Integer prazo;

    public Produto converteModelo(){
        Produto produto = new Produto();
        produto.setTitulo(titulo);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setMiniatura(miniatura);
        produto.setPrazo(prazo);

        return produto;
    }
}
