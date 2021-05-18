package com.uniamerica.unijobsbackend.dto;

import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TipoServicoDTO {
    private Integer id_tipoServico;
    private String nome;
    private String descricao;
    private List<ServicoDTO> servico = new ArrayList<>();

    public TipoServicoDTO(TipoServico tipoServico) {
        this.id_tipoServico = tipoServico.getId_tipoServico();
        this.nome = tipoServico.getNome();
        this.descricao = tipoServico.getDescricao();
    }
}
