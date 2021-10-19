package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.TipoProduto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TesteTipoProdutoRepository {

    @Autowired
    public RepositorioTipoProduto repositorio;

    @Test
    public void CriacaoTipoProduto(){
        TipoProduto teste = new TipoProduto(null, "Comercial", "Produto de carater comercial");
        TipoProduto save = repositorio.save(teste);
        teste.setId_tipo_produto(save.getId_tipo_produto());

        assertEquals(save, teste);
    }

    @Test
    public void EdicaoDeTipoProduto(){
        TipoProduto teste = new TipoProduto(1, "Comercial", "Produto de carater comercial");
        teste.setNome("piriri Pororo");
        TipoProduto save = repositorio.save(teste);
        assertEquals(save.getNome(), teste.getNome());
    }

    @Test
    public void DeletarTipoProduto(){
        TipoProduto teste = new TipoProduto(null, "Comercial", "Produto de carater comercial");
        TipoProduto save = repositorio.save(teste);
        assertDoesNotThrow(() -> repositorio.deleteById(save.getId_tipo_produto()));
    }
}
