package com.uniamerica.unijobsbackend.produto;

import com.uniamerica.unijobsbackend.tipoProduto.tipoProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_produto;

    private String titulo;
    private String descricao;
    private Double preco;
    private String miniatura;
    private Boolean ativo;
    private Integer prazo;
    private Integer id_usuario;

    @ManyToOne
    private tipoProduto tipoProduto;
}
