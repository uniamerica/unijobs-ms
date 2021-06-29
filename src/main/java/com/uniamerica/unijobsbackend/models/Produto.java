package com.uniamerica.unijobsbackend.models;

import com.uniamerica.unijobsbackend.models.TipoProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
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

    private Integer id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_tipo_produto")
    private TipoProduto tipoProduto;
}
