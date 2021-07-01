package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.Excessoes.RecursoNaoEncontradoExcessao;
import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.repositories.RepositorioProduto;
import com.uniamerica.unijobsbackend.repositories.RepositorioTipoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class ProdutoService {
    @Autowired
    private final RepositorioProduto repositorioProduto;

    @Autowired
    private final RepositorioTipoProduto repositorioTipoProduto;

    public ProdutoService(RepositorioProduto repositorioProduto, RepositorioTipoProduto repositorioTipoProduto) {
        this.repositorioProduto = repositorioProduto;
        this.repositorioTipoProduto = repositorioTipoProduto;
    }

    public List<Produto> VisualizarProduto(){
        return repositorioProduto.findAll();
    }

    public Produto CadastrarProduto(Produto produto) {
        Integer id_tipo_produto = produto.getTipoProduto().getId_tipo_produto();

        var produto1 = repositorioTipoProduto.findById(id_tipo_produto)
          .orElseThrow(
            () -> new RecursoNaoEncontradoExcessao("Categoria não Encontrada! id:" + id_tipo_produto)
          );
        produto.setTipoProduto(produto1);

        return repositorioProduto.save(produto);
    }

    public String DeletarProduto(Integer id) {
        boolean existe = repositorioProduto.existsById(id);
        if(!existe){
            throw new RecursoNaoEncontradoExcessao("Produto não Existe. id: " + id);
        }
        repositorioProduto.deleteById(id);
        return "Produto deletado com sucesso!";
    }

    @Transactional
    public Produto EditarProduto(Integer id, Produto novoProduto) {
        Produto produto1 = repositorioProduto.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Produto não Existe. id: " + id)
                );
        produto1.setTitulo(novoProduto.getTitulo());
        produto1.setDescricao(novoProduto.getDescricao());
        produto1.setMiniatura(novoProduto.getMiniatura());
        produto1.setPrazo(novoProduto.getPrazo());
        produto1.setPreco(novoProduto.getPreco());
        produto1.setTipoProduto(novoProduto.getTipoProduto());
        produto1.setAtivo(novoProduto.getAtivo());
        return produto1;
    }
}
