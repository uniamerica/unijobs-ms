package com.uniamerica.unijobsbackend.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Servico servico = (Servico) o;

        return id_servico != null && id_servico.equals(servico.id_servico);
    }

    @Override
    public int hashCode() {
        return 1228432928;
    }
}
