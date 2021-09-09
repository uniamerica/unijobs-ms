package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.TipoProduto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatObject;

@SpringBootTest
public class TesteTipoProdutoRepository {

    @Autowired
    public RepositorioTipoProduto repositorio;

    @Test
    public void CriacaoTipoProduto(){
        TipoProduto teste = new TipoProduto(1, "Comercial", "Produto de carater comercial");
        TipoProduto save = repositorio.save(teste);
        Assertions.assertEquals(save, teste);
    }

    @Test
    public void EdicaoDeTipoProduto(){
        TipoProduto teste = new TipoProduto(1, "Comercial", "Produto de carater comercial");
        teste.setNome("piriri Pororo");
        TipoProduto save = repositorio.save(teste);
        Assertions.assertEquals(save.getNome(), teste.getNome());
    }

    @Test
    public void DeletarTipoProduto(){
        TipoProduto teste = new TipoProduto(1, "Comercial", "Produto de carater comercial");
        TipoProduto save = repositorio.save(teste);
        repositorio.deleteById(0);
        Assertions.assertNotEquals(repositorio.findById(0), teste);
    }
}
