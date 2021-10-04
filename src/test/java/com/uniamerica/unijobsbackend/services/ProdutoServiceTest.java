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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;
    @Mock
    private RepositorioProduto repositorioProdutoMock;
    @Mock
    private UsuarioRepository usuarioRepositoryMock;
    @Mock
    private RepositorioTipoProduto repositorioTipoProdutoMock;

    @Test
    @DisplayName("O usuario deve registar ou cadastrar um novo produto sem conflito de mesmo id")
    void cadastrarProduto() {

        TipoProduto tipo = new TipoProduto(1);
        Usuario usuario = new Usuario(1);

        Produto produtoteste = new Produto(
                0,
                "Titulo teste",
                "descricao teste",
                12.0,
                "Miniatura teste",
                false,
                28,
                tipo,
                usuario
        );

        when(repositorioTipoProdutoMock.findById(any())).thenReturn(Optional.of(tipo));
        when(usuarioRepositoryMock.findById(any())).thenReturn(Optional.of(usuario));
        when(repositorioProdutoMock.save(any())).thenReturn(produtoteste);
        when(repositorioProdutoMock.findById(any())).thenReturn(Optional.of(produtoteste));

        Assertions.assertEquals(produtoteste, produtoService.CadastrarProduto(produtoteste));
        Assertions.assertNotEquals(null, produtoService.CadastrarProduto(produtoteste));
        Assertions.assertEquals(produtoteste, produtoService.BuscarProduto(produtoteste.getId_produto()));
    }

    @Test
    @DisplayName("O usuario não deve conseguir registar ou cadastrar um novo produto sem usuario ou tipo de produto")
    void cadastrarProdutoSemUsuarioETipoProduto() {
        Produto p = new Produto(
                0,
                "Titulo teste",
                "descricao teste",
                12.0,
                "Miniatura teste",
                false,
                28,
                null,
                null
        );

        when(repositorioProdutoMock.save(any())).thenReturn(p);
        when(repositorioProdutoMock.findById(any())).thenReturn(Optional.of(p));

        Assertions.assertThrows(NullPointerException.class, () ->  produtoService.CadastrarProduto(p));
    }


    @Test
    @DisplayName("O usuario não deve conseguir deletar um produto")
    void deletarProduto() {
        TipoProduto tipo = new TipoProduto(99);
        Usuario usuario = new Usuario(99);

        Produto p = new Produto(
                140,
                "Titulo teste",
                "descricao teste",
                12.0,
                "Miniatura teste",
                false,
                28,
                tipo,
                usuario
        );


        when(repositorioTipoProdutoMock.findById(any())).thenReturn(Optional.of(tipo));
        when(usuarioRepositoryMock.findById(any())).thenReturn(Optional.of(usuario));
        when(repositorioProdutoMock.findById(anyInt())).thenReturn(Optional.of(p));

        Assertions.assertEquals( "NullPointerException.class", produtoService.BuscarProduto(99));
        Assertions.assertEquals(p, produtoService.CadastrarProduto(p));
        Assertions.assertNotEquals(null, produtoService.CadastrarProduto(p));
        Assertions.assertEquals(p, produtoService.BuscarProduto(p.getId_produto()));
        Assertions.assertDoesNotThrow( () -> produtoService.DeletarProduto(p.getId_produto()));
        Assertions.assertEquals(p, produtoService.DeletarProduto(p.getId_produto()));
        Assertions.assertNotEquals(p, produtoService.BuscarProduto(99));
    }

    @Test
    @DisplayName("O usuario deve conseguir editar um produto")
    void editarProduto() {
        TipoProduto tipo = new TipoProduto(99);

        TipoProduto tipo2 = new TipoProduto(99);

        Usuario usuario = new Usuario(99);

        Usuario usuario2 = new Usuario(99);

        Produto p = new Produto(
                99,
                "Titulo teste",
                "descricao teste",
                12.0,
                "Miniatura teste",
                false,
                28,
                tipo,
                usuario
        );

        Produto p2 = new Produto(
                104,
                "Titulo teste modificado",
                "descricao teste modificado",
                15.0,
                "Miniatura teste modificada",
                true,
                20,
                tipo2,
                usuario2
        );

        when(repositorioTipoProdutoMock.findById(any())).thenReturn(Optional.of(tipo));
        when(usuarioRepositoryMock.findById(any())).thenReturn(Optional.of(usuario));
        when(repositorioProdutoMock.save(any())).thenReturn(p);
        when(repositorioProdutoMock.findById(any())).thenReturn(Optional.of(p));
        produtoService.EditarProduto(99, p2);

        Assertions.assertEquals(p2, produtoService.BuscarProduto(99));
    }

    @Test
    @DisplayName("O usuario não deve conseguir editar um produto")
    void buscarProduto() {

        TipoProduto tipo = new TipoProduto(99);
        Usuario usuario = new Usuario(99);

        Produto p1 = new Produto(1, "Titulo teste","descricao teste",10.0,"Miniatura teste",false,28,tipo,usuario);
        Produto p2 = new Produto(2, "Titulo teste","descricao teste",23.0,"Miniatura teste",false,28,tipo,usuario);
        Produto p3 = new Produto(3, "Titulo teste","descricao teste",47.0,"Miniatura teste",false,28,tipo,usuario);
        Produto p4 = new Produto(4, "Titulo teste","descricao teste",93.0,"Miniatura teste",false,28,tipo,usuario);

        when(repositorioTipoProdutoMock.findById(any())).thenReturn(Optional.of(tipo));
        when(usuarioRepositoryMock.findById(any())).thenReturn(Optional.of(usuario));
        when(repositorioProdutoMock.save(p1)).thenReturn(p1);
        when(repositorioProdutoMock.save(p2)).thenReturn(p2);
        when(repositorioProdutoMock.save(p3)).thenReturn(p3);
        when(repositorioProdutoMock.save(p4)).thenReturn(p4);
        when(repositorioProdutoMock.findById(1)).thenReturn(Optional.of(p1));
        when(repositorioProdutoMock.findById(2)).thenReturn(Optional.of(p2));
        when(repositorioProdutoMock.findById(3)).thenReturn(Optional.of(p3));
        when(repositorioProdutoMock.findById(4)).thenReturn(Optional.of(p4));


        Assertions.assertEquals(p1 ,produtoService.CadastrarProduto(p1));
        Assertions.assertEquals(p1 ,produtoService.BuscarProduto(1));
        Assertions.assertEquals(p2 ,produtoService.CadastrarProduto(p2));
        Assertions.assertEquals(p2 ,produtoService.BuscarProduto(2));
        Assertions.assertEquals(p3 ,produtoService.CadastrarProduto(p3));
        Assertions.assertEquals(p3 ,produtoService.BuscarProduto(3));
        Assertions.assertEquals(p4 ,produtoService.CadastrarProduto(p4));
        Assertions.assertEquals(p4 ,produtoService.BuscarProduto(4));


        Assertions.assertNotNull(produtoService.VisualizarProduto());
    }
}