package com.uniamerica.unijobsbackend.tipoServico;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "tipos_servico")
public class TipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipoServico;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A Descrição é obrigatória")
    private String descricao;

    public TipoServico(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public TipoServico() {

    }
}
