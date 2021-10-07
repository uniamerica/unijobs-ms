package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.models.TipoProduto;
import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.repositories.RepositorioProduto;
import com.uniamerica.unijobsbackend.repositories.RepositorioTipoProduto;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TipoProdutoServiceTest {

    @InjectMocks
    private TipoProdutoService serviceTipoProduto;
    @InjectMocks
    private ProdutoService serviceProduto;
    @Mock
    private UsuarioRepository repositorioUsuario;
    @Mock
    private RepositorioTipoProduto repositorioTipoProduto;
    @Mock
    private RepositorioProduto repositorioProduto;

    @Test
    void visualizarTipoProduto() {
    }

    @Test
    void cadastrarTipoProduto() {
        TipoProduto tipo = new TipoProduto(1, "Alimenticio", "Setor de alimentos e venda de alimentos");

        when(repositorioTipoProduto.findById(any())).thenReturn(Optional.of(tipo));
        when(repositorioTipoProduto.save(tipo)).thenReturn(tipo);
        Assertions.assertEquals(tipo, serviceTipoProduto.CadastrarTipoProduto(tipo));
        Assertions.assertEquals(tipo, serviceTipoProduto.BuscarTipoProduto(1));
    }

    @Test
    void deletarTipoProduto() {
        TipoProduto tipo = new TipoProduto(1, "Alimenticio", "Setor de alimentos e venda de alimentos");
        when(repositorioTipoProduto.findById(any())).thenReturn(Optional.empty());
        Assertions.assertDoesNotThrow(()-> serviceTipoProduto.CadastrarTipoProduto(tipo));
        Assertions.assertDoesNotThrow(()-> serviceTipoProduto.DeletarTipoProduto(1));
    }

    @Test
    void editarTipoProduto() {
        TipoProduto tipo = new TipoProduto(0, "Alimenticio", "Setor de alimentos e venda de alimentos");
        TipoProduto tipo2 = new TipoProduto(12, "Alimenticio ( alterado )", "Setor de alimentos e venda de alimentos ( alterado )");
        when(repositorioTipoProduto.findById(any())).thenReturn(Optional.of(tipo));
        when(repositorioTipoProduto.save(tipo)).thenReturn(tipo);

        Assertions.assertEquals(tipo, serviceTipoProduto.CadastrarTipoProduto(tipo));
        tipo2.setId_tipo_produto(0);
        Assertions.assertEquals(tipo2, serviceTipoProduto.EditarTipoProduto(0, tipo2));
        Assertions.assertEquals(tipo2, serviceTipoProduto.BuscarTipoProduto(0));
    }

    /*@Test
    void servicosByTipoProdutos() {
        TipoProduto tipo = new TipoProduto(1, "Alimenticio", "Setor de alimentos e venda de alimentos");
        TipoProduto tipo2 = new TipoProduto(2, "Esportes", "Setor de Esportes e venda de Equipamentos esportivos");
        Usuario usuario = new Usuario(1);
        Produto p1 = new Produto(1, "Trufa de Cacau", "é uma trufa de cacau artesanal", 20.0, "miniatura dazora", true, 10, tipo, usuario);
        Produto p2 = new Produto(2, "Trufa de Maracuja", "é uma trufa de Maracuja artesanal", 40.0, "miniatura dazora", true, 10, tipo, usuario);
        Produto p3 = new Produto(3, "Trufa de Leite Ninho", "é uma trufa de Leite Ninho artesanal", 60.0, "miniatura dazora", true, 10, tipo, usuario);
        Produto p4 = new Produto(4, "Skate Chorão", "é uma SsKkAaTtEe do C H O R Ã O", 120.0, "miniatura dazora", true, 10, tipo2, usuario);

        when(repositorioTipoProduto.findById(any())).thenReturn(Optional.of(tipo));
        when(repositorioTipoProduto.save(tipo)).thenReturn(tipo);
        when(repositorioTipoProduto.save(tipo2)).thenReturn(tipo2);
        when(repositorioUsuario.findById(any())).thenReturn(Optional.of(usuario));
        when(repositorioProduto.save(p1)).thenReturn(p1);
        when(repositorioProduto.save(p2)).thenReturn(p2);
        when(repositorioProduto.save(p3)).thenReturn(p3);
        when(repositorioProduto.save(p4)).thenReturn(p4);

        Assertions.assertEquals(tipo, serviceTipoProduto.CadastrarTipoProduto(tipo));
        Assertions.assertEquals(tipo2, serviceTipoProduto.CadastrarTipoProduto(tipo2));
        Assertions.assertEquals(p1, serviceProduto.CadastrarProduto(p1));
        Assertions.assertEquals(p2, serviceProduto.CadastrarProduto(p2));
        Assertions.assertEquals(p3, serviceProduto.CadastrarProduto(p3));
        Assertions.assertEquals(p4, serviceProduto.CadastrarProduto(p4));

        List<Produto> lista1 = serviceTipoProduto.servicosByTipoProdutos(1);
        List<Produto> lista2 = serviceTipoProduto.servicosByTipoProdutos(2);

        Assertions.assertNotEquals(0, lista1.size());
        Assertions.assertNotEquals(0, lista2.size());

        Assertions.assertEquals(p1, lista1.get(0));
        Assertions.assertEquals(p2, lista1.get(1));
        Assertions.assertEquals(p3, lista1.get(2));
        Assertions.assertEquals(p4, lista2.get(0));
    }*/
}