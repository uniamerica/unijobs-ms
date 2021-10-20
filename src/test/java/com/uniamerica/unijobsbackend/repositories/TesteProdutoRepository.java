package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Produto p = new Produto(null, "produto titulo", "descricao produto", 28.0, "miniatura produto", false, 28, null, null);
        repositorio.save(p);
        Assertions.assertEquals(repositorio.findById(p.getId_produto()).orElseThrow(), p);
    }

    @Test
    public void EdicaoDeProduto(){
        Produto p = new Produto(0, "produto titulo", "descricao produto", 28.0, "miniatura produto", false, 28, null, null);
        Produto save = repositorio.save(p);
        p.setTitulo("piriri Pororo");
        save = repositorio.save(p);
        Assertions.assertEquals(save.getTitulo(), p.getTitulo());
    }

    @Test
    public void DeletarProduto(){
        Produto p = new Produto(null, "produto titulo", "descricao produto", 28.0, "miniatura produto", false, 28, null, null);
        Produto save = repositorio.save(p);
        assertDoesNotThrow(() -> repositorio.deleteById(save.getId_produto()));
    }
}
