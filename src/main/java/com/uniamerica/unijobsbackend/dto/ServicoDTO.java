package com.uniamerica.unijobsbackend.dto;

import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import lombok.Data;

import java.io.Serializable;

@Data
public class ServicoDTO implements Serializable {
    private Integer id;
    private String titulo;
    private String descricao;
    private Double preco;
    private String miniatura;
    private boolean ativo;
    private Integer prazo;

    private TipoServicoDTO tipoServico;

    public ServicoDTO(Servico servico) {
        id = servico.getId_servico();
        titulo = servico.getTitulo();
        descricao = servico.getDescricao();
        preco = servico.getPreco();
        miniatura = servico.getMiniatura();
        ativo = servico.isAtivo();
        prazo = servico.getPrazo();
        tipoServico = new TipoServicoDTO(servico.getTipoServico());
    }
}
