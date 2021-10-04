package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.Excessoes.RecursoNaoEncontradoExcessao;
import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.repositories.RepositorioProduto;
import com.uniamerica.unijobsbackend.repositories.RepositorioTipoProduto;
import com.uniamerica.unijobsbackend.models.TipoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TipoProdutoService {
    @Autowired
    private final RepositorioTipoProduto repositorioTipoProduto;

    private final RepositorioProduto repositorioProduto;

    public TipoProdutoService(RepositorioTipoProduto repositorioTipoProduto, RepositorioProduto repositorioProduto) {
        this.repositorioTipoProduto = repositorioTipoProduto;
        this.repositorioProduto = repositorioProduto;
    }

    public List<TipoProduto> VisualizarTipoProduto(){
        return repositorioTipoProduto.findAll();
    }

    public TipoProduto CadastrarTipoProduto(TipoProduto tipoProduto) {
        validacaoNome(tipoProduto.getNome());
        return repositorioTipoProduto.save(tipoProduto);
    }

    public String DeletarTipoProduto(Integer id_tipoProduto) {
        boolean existe = repositorioTipoProduto.existsById(id_tipoProduto);
        if(existe){
            throw new IllegalStateException("Categoria não Existe. id: " + id_tipoProduto);
        }
        repositorioTipoProduto.deleteById(id_tipoProduto);
        return "Categoria deletada com sucesso!";
    }

    @Transactional
    public TipoProduto EditarTipoProduto(Integer id_tipoProduto, TipoProduto tipoProduto) {
        TipoProduto tipoProduto1 = repositorioTipoProduto.findById(id_tipoProduto)
                .orElseThrow(
                        () -> new RecursoNaoEncontradoExcessao("TipoProduto não Existe. id: " + id_tipoProduto)
        );

        if(tipoProduto.getNome() != null && !Objects.equals(tipoProduto1.getNome(), tipoProduto.getNome())){tipoProduto1.setNome(tipoProduto.getNome()); }
        if (tipoProduto.getDescricao() != null && !Objects.equals(tipoProduto1.getDescricao(), tipoProduto.getDescricao())){tipoProduto1.setDescricao(tipoProduto.getDescricao());}
        tipoProduto1.setId_tipo_produto(tipoProduto.getId_tipo_produto());

        return tipoProduto1;
    }

    public TipoProduto BuscarTipoProduto(Integer id) {
        Optional<TipoProduto> tipo = repositorioTipoProduto.findById(id);
        var i = tipo.isPresent();
        if (i){
            return tipo.get();
        } else {
            return null;
        }
    }

    public void validacaoNome(String nome){
        Optional<TipoProduto> TipoProdutoPorNome = repositorioTipoProduto.findTipoProdutoByNome(nome);
        if(TipoProdutoPorNome.isPresent()){
            throw new IllegalStateException("Categoria Já Existente");
        }
    }

    public List<Produto> servicosByTipoProdutos(Integer id){
        TipoProduto tipoProduto = repositorioTipoProduto.findById(id)
                .orElseThrow(
                        () -> new RecursoNaoEncontradoExcessao("Tipo Produto não Encontrado! id:" + id)
                );
        List<Produto> produtos = repositorioProduto.findByTipoProduto(tipoProduto, PageRequest.of(0, 5));
        return produtos;
    }
}
