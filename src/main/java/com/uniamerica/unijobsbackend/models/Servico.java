package com.uniamerica.unijobsbackend.models;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
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

    private String titulo;

    private String descricao;

    private Double preco;

    private String miniatura;

    private boolean ativo;

    private Integer prazo;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_tipo_servico", nullable = false)
    private TipoServico tipoServico;
}
