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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
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
    @DisplayName("O usuario deve registar ou cadastrar um novo produto")
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

        Assertions.assertDoesNotThrow(() -> produtoService.CadastrarProduto(produtoteste));
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

        when(repositorioTipoProdutoMock.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NullPointerException.class, () ->  produtoService.CadastrarProduto(p));
    }


    @Test
    @DisplayName("O usuario não deve conseguir deletar um produto")
    void deletarProduto() {
        TipoProduto tipo = new TipoProduto(99);
        Usuario usuario = new Usuario(99);

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


        when(repositorioTipoProdutoMock.findById(any())).thenReturn(Optional.of(tipo));
        when(usuarioRepositoryMock.findById(any())).thenReturn(Optional.of(usuario));
        when(repositorioProdutoMock.findById(any())).thenReturn(Optional.of(p));
        when(produtoService.CadastrarProduto(p)).thenReturn(p);

        Assertions.assertDoesNotThrow(() -> {
            produtoService.DeletarProduto(99);
        });
    }

    @Test
    public void editProduto(){

        TipoProduto tipo = new TipoProduto(99);
        Usuario usuario = new Usuario(99);

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

    }

    @Test
    void buscarProduto() {
    }
}