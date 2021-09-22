package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.Produto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThatObject;

@DataJpaTest
public class TesteProdutoRepository {

    @Autowired
    private RepositorioProduto repositorio;

    @AfterEach
    void ResetarTeste() {
        repositorio.deleteAll();
    }

    @Test
    public void CriacaoProduto(){
        Produto p = new Produto(0, "produto titulo", "descricao produto", 28.0, "miniatura produto", false, 28, null, null);
        repositorio.save(p);
        Assertions.assertEquals(repositorio.findById(0), p);
    }

    /*@Test
    public void EdicaoDeProduto(){
        Produto p = new Produto(0, "produto titulo", "descricao produto", 28.0, "miniatura produto", false, 28, null, null);
        Produto save = repositorio.save(p);
        p.setTitulo("piriri Pororo");
        save = repositorio.save(p);
        Assertions.assertEquals(save.getTitulo(), p.getTitulo());
    }

    @Test
    public void DeletarProduto(){
        Produto p = new Produto(0, "produto titulo", "descricao produto", 28.0, "miniatura produto", false, 28, null, null);
        Produto save = repositorio.save(p);
        repositorio.deleteById(0);
        Assertions.assertNotEquals(repositorio.findById(0), p);
    }*/
}
