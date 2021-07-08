package com.uniamerica.unijobsbackend.dto.input;

import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.models.TipoProduto;
import com.uniamerica.unijobsbackend.models.Usuario;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NovoProdutoDTO {
    @NotBlank(message = "O Título é obrigatório.")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "O preço é obrigatório.")
    private Double preco;

    @NotBlank(message = "A miniatura é obrigatória.")
    private String miniatura;

    @NotNull(message = "O prazo é obrigatório.")
    private Integer prazo;

    @ManyToOne
    @JoinColumn(name = "id_tipo_produto")
    private TipoProduto tipoProduto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @NotNull(message = "O tipo produto é obrigatório")
    private Integer id_tipo_produto;
    @NotNull(message = "O Usuario é obrigatório")
    private Integer id_usuario;

    public Produto converteModelo(){
        Produto produto = new Produto();
        produto.setTitulo(titulo);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setMiniatura(miniatura);
        produto.setPrazo(prazo);
        produto.setTipoProduto(new TipoProduto(id_tipo_produto));
        produto.setUsuario(new Usuario(id_usuario));

        return produto;
    }
}
