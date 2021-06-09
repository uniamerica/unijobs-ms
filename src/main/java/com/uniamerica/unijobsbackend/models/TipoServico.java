package com.uniamerica.unijobsbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tipos_servico")
public class TipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipo_servico;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A Descrição é obrigatória")
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoServico")
    private List<Servico> servico = new ArrayList<>();

    public TipoServico(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public TipoServico(Integer id_tipo_servico) {
        this.id_tipo_servico = id_tipo_servico;
    }
}
