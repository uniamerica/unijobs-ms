package com.uniamerica.unijobsbackend.tipoProduto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TipoProdutoDTO {
        private Integer id_tipo_produto;
        private String nome;
        private String descricao;
        private List<TipoProdutoDTO> tipoProdutoDTOS = new ArrayList<>();

        public TipoProdutoDTO(TipoProduto tipoProduto) {
            this.id_tipo_produto = tipoProduto.getId_tipo_produto();
            this.nome = tipoProduto.getNome();
            this.descricao = tipoProduto.getDescricao();
        }
}
