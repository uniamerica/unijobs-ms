package com.uniamerica.unijobsbackend.Testes;

import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.repositories.RepositorioProduto;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TesteProdutoRepository {


    RepositorioProduto repository = new RepositorioProduto();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @AfterEach
    void ResetarTeste() {
        repository.deleteAll();
    }

    @Test
    public void CriacaoProduto(){
        Produto p = new Produto(0, "produto titulo", "descricao produto", 28.0, "miniatura produto", false, 28, null, null);
        this.repository.save(p);
        Assertions.assertEquals(repository.findById(0), p);
    }

    @Test
    public void EdicaoDeProduto(){
        Produto p = new Produto(0, "produto titulo", "descricao produto", 28.0, "miniatura produto", false, 28, null, null);
        Produto save = repository.save(p);
        p.setTitulo("piriri Pororo");
        save = repository.save(p);
        Assertions.assertEquals(save.getTitulo(), p.getTitulo());
    }

    @Test
    public void DeletarProduto(){
        Produto p = new Produto(0, "produto titulo", "descricao produto", 28.0, "miniatura produto", false, 28, null, null);
        Produto save = repository.save(p);
        repository.deleteById(0);
        Assertions.assertNotEquals(save, p);
    }
}
