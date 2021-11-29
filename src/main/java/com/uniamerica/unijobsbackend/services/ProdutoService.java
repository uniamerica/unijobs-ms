package com.uniamerica.unijobsbackend.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.uniamerica.unijobsbackend.Excessoes.RecursoNaoEncontradoExcessao;
import com.uniamerica.unijobsbackend.configs.CloudinarySingleton;
import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.repositories.RepositorioProduto;
import com.uniamerica.unijobsbackend.repositories.RepositorioTipoProduto;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final RepositorioProduto repositorioProduto;

    private final UsuarioRepository usuarioRepository;

    private final RepositorioTipoProduto repositorioTipoProduto;

    public Page<Produto> findAll(Pageable pageable) {
        return repositorioProduto.findAll(pageable);
    }

    public Produto create(Produto produto) {
        Integer idTipoProduto = produto.getTipoProduto().getId_tipo_produto();
        Integer idUsuario = produto.getUsuario().getId();

        var tipo = repositorioTipoProduto
                .findById(idTipoProduto)
                .orElseThrow(throwException("Categoria não Encontrada! id:" + idTipoProduto));

        var usuario = usuarioRepository
                .findById(idUsuario)
                .orElseThrow(throwException("Usuario não Encontrado! id:" + idUsuario));

        produto.setTipoProduto(tipo);
        produto.setAtivo(true);
        produto.setUsuario(usuario);

        Cloudinary cloudinary = CloudinarySingleton.getCloudinary();
        try {
            var uploadResult = cloudinary.uploader()
                    .upload(produto.getMiniaturaBytes(), ObjectUtils.emptyMap());
            produto.setMiniatura((String) uploadResult.get("url"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repositorioProduto.save(produto);
    }

    public String delete(Integer id) {
        boolean existe = repositorioProduto.existsById(id);
        if (!existe) {
            throwException("Produto não Existe. id: " + id).get();
        }
        repositorioProduto.deleteById(id);
        return "Produto deletado com sucesso!";
    }

    public Produto update(Integer id, Produto novoProduto) {
        Produto produto = repositorioProduto
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Produto não Existe. id: " + id));

        produto.setTitulo(novoProduto.getTitulo());
        produto.setDescricao(novoProduto.getDescricao());
        produto.setMiniatura(novoProduto.getMiniatura());
        produto.setPrazo(novoProduto.getPrazo());
        produto.setPreco(novoProduto.getPreco());
        produto.setTipoProduto(novoProduto.getTipoProduto());
        produto.setAtivo(novoProduto.getAtivo());
        return produto;
    }

    public Produto findById(Integer id) {
        return repositorioProduto.findById(id)
                .orElseThrow(() -> new IllegalStateException("Produto não Existe. id: " + id));
    }

    private Supplier<RecursoNaoEncontradoExcessao> throwException(String message) {
        return () -> new RecursoNaoEncontradoExcessao(message);
    }

    public Page<Produto> findBySearch(String search, Pageable pageable) {
        return repositorioProduto.findBySearch("%" + search + "%", pageable);
    }
}
