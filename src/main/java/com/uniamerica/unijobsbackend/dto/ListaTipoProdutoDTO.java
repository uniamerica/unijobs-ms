package com.uniamerica.unijobsbackend.dto;

import com.uniamerica.unijobsbackend.models.TipoProduto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ListaTipoProdutoDTO implements Serializable {
    private Integer id_tipo_produto;
    private String nome;
    private String descricao;
}
