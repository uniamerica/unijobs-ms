package com.uniamerica.unijobsbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
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
    @ToString.Exclude
    private List<Servico> servico = new ArrayList<>();

    public TipoServico(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public TipoServico(Integer id_tipo_servico, String nome, String descricao) {
        this.id_tipo_servico = id_tipo_servico;
        this.nome = nome;
        this.descricao = descricao;
    }

    public TipoServico(Integer id_tipo_servico) {
        this.id_tipo_servico = id_tipo_servico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TipoServico that = (TipoServico) o;

        return id_tipo_servico != null && id_tipo_servico.equals(that.id_tipo_servico);
    }

    @Override
    public int hashCode() {
        return 1145590354;
    }
}
