package com.uniamerica.unijobsbackend.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "servicos")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_servico;

    @NotBlank(message = "Titulo é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;

    @NotBlank(message = "Preço é obrigatório")
    private Double preco;

    private String miniatura;

    private boolean ativo;

    private Integer prazo;

    @ManyToOne
    @JoinColumn(name = "id_tipoServico", nullable = false)
    private TipoServico tipoServico;
}
