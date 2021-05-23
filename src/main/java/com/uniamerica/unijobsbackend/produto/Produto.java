package com.uniamerica.unijobsbackend.produto;

import com.uniamerica.unijobsbackend.tipoProduto.tipoProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_produto;

    @NotBlank(message = "O Título é obrigatório.")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotBlank(message = "O Preço é obrigatório.")
    private Double preco;

    @NotBlank(message = "É obrigatório adicionar uma imagem do produto.")
    private String miniatura;

    private Boolean ativo;

    @NotBlank(message = "O Prazo é obrigatório.")
    private Integer prazo;

    private Integer id_usuario;

    @ManyToOne
    @NotBlank(message = "A categoria do Produto é obrigatória.")
    private tipoProduto tipoProduto;
}
