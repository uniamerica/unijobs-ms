package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DataJpaTest
public class ProdutoRepositoryTest {

    @Autowired
    private RepositorioProduto repositorio;

    @AfterEach
    void ResetarTeste() {
        repositorio.deleteAll();
    }

    @Test
    public void CriacaoProduto() {
        Produto p = Produto.builder()
                .titulo("produto titulo")
                .descricao("descricao")
                .preco(28.0)
                .prazo(28)
                .build();
        repositorio.save(p);
        Assertions.assertEquals(repositorio.findById(p.getId_produto()).orElseThrow(), p);
    }

    @Test
    public void EdicaoDeProduto() {
        Produto p = Produto.builder()
                .titulo("produto titulo")
                .descricao("descricao")
                .preco(28.0)
                .prazo(28)
                .build();
        Produto save = repositorio.save(p);
        p.setTitulo("piriri Pororo");
        save = repositorio.save(p);
        Assertions.assertEquals(save.getTitulo(), p.getTitulo());
    }

    @Test
    public void DeletarProduto() {
        Produto p = Produto.builder()
                .titulo("produto titulo")
                .descricao("descricao")
                .preco(28.0)
                .prazo(28)
                .build();
        Produto save = repositorio.save(p);
        assertDoesNotThrow(() -> repositorio.deleteById(save.getId_produto()));
    }
}
