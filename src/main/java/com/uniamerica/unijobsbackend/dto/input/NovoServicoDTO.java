package com.uniamerica.unijobsbackend.dto.input;

import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.models.Usuario;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NovoServicoDTO {
    @NotBlank(message = "Titulo é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;

    @NotNull(message = "O preço é obrigatório.")
    private Double preco;

    @NotBlank(message = "A Miniatura é obrigatório")
    private String miniatura;
    
    @NotNull(message = "O prazo é obrigatório.")
    private Integer prazo;

    @NotNull(message = "O tipo servico é obrigatório")
    private Integer id_tipo_servico;

    @NotNull(message = "O Usuario é obrigatório")
    private Integer id_usuario;

    public NovoServicoDTO(String titulo, String descricao, double preco, String miniatura, Integer prazo, Integer id_tipo_servico, Integer id_usuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.miniatura = miniatura;
        this.prazo = prazo;
        this.id_tipo_servico = id_tipo_servico;
        this.id_usuario = id_usuario;
    }

    public Servico converteModelo(){
        Servico servico = new Servico();
        servico.setTitulo(titulo);
        servico.setDescricao(descricao);
        servico.setPreco(preco);
        servico.setMiniatura(miniatura);
        servico.setPrazo(prazo);
        servico.setTipoServico(new TipoServico(id_tipo_servico));
        servico.setUsuario(new Usuario(id_usuario));

        return servico;
    }
    
}
