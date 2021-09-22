package com.uniamerica.unijobsbackend.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.uniamerica.unijobsbackend.Excessoes.RecursoNaoEncontradoExcessao;
import com.uniamerica.unijobsbackend.configs.CloudinarySingleton;
import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.models.TipoProduto;
import com.uniamerica.unijobsbackend.repositories.RepositorioProduto;
import com.uniamerica.unijobsbackend.repositories.RepositorioTipoProduto;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class ProdutoService {
    @Autowired
    private final RepositorioProduto repositorioProduto;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final RepositorioTipoProduto repositorioTipoProduto;

    public ProdutoService(RepositorioProduto repositorioProduto, RepositorioTipoProduto repositorioTipoProduto, UsuarioRepository usuarioRepository) {
        this.repositorioProduto = repositorioProduto;
        this.repositorioTipoProduto = repositorioTipoProduto;
        this.usuarioRepository = usuarioRepository;
    }

    public Page<Produto> VisualizarProduto(Pageable pageable){
        return repositorioProduto.findAll(pageable);
    }

    public Produto CadastrarProduto(Produto produto) {

        try{
            Integer id_tipo_produto = produto.getTipoProduto().getId_tipo_produto();
            Integer id_usuario = produto.getUsuario().getId();
        } catch(Error error){
            new RecursoNaoEncontradoExcessao();
        }

        Integer id_tipo_produto = produto.getTipoProduto().getId_tipo_produto();
        Integer id_usuario = produto.getUsuario().getId();

        TipoProduto tipo = repositorioTipoProduto.findById(id_tipo_produto)
          .orElseThrow(
            () -> new RecursoNaoEncontradoExcessao("Categoria não Encontrada! id:" + id_tipo_produto)
          );

        var usuario = usuarioRepository.findById(id_usuario)
            .orElseThrow(
                () -> new RecursoNaoEncontradoExcessao("Usuario não Encontrado! id:" + id_usuario)
            );

        produto.setTipoProduto(produto.getTipoProduto());
        produto.setAtivo(produto.getAtivo());
        produto.setUsuario(produto.getUsuario());

        String minituraUrl = uploadMiniatura(produto.getMiniatura());
        produto.setMiniatura(minituraUrl);

        return repositorioProduto.save(produto);
    }

    public String DeletarProduto(Integer id) {
        boolean existe = repositorioProduto.existsById(id);
        if(!existe){
            throw new RecursoNaoEncontradoExcessao("Produto não Existe. id: " + id);
        }
        repositorioProduto.deleteById(id);
        return "Produto deletado com sucesso!";
    }

    @Transactional
    public Produto EditarProduto(Integer id, Produto novoProduto) {
        Produto produto1 = repositorioProduto.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Produto não Existe. id: " + id)
                );
        produto1.setTitulo(novoProduto.getTitulo());
        produto1.setDescricao(novoProduto.getDescricao());
        produto1.setMiniatura(novoProduto.getMiniatura());
        produto1.setPrazo(novoProduto.getPrazo());
        produto1.setPreco(novoProduto.getPreco());
        produto1.setTipoProduto(novoProduto.getTipoProduto());
        produto1.setAtivo(novoProduto.getAtivo());
        return produto1;
    }

    public Produto BuscarProduto(Integer id) {
        return repositorioProduto.findById(id).orElseThrow(
            () -> new IllegalStateException("Produto não Existe. id: " + id)
        );
    }

    private String uploadMiniatura(String miniatura) {
        Cloudinary cloudinary = CloudinarySingleton.getCloudinary();
        try {
            var uploadResult = cloudinary.uploader().upload(miniatura, ObjectUtils.emptyMap());
            return (String) uploadResult.get("url");
        } catch (IOException e) {
            return null;
        }
    }
}
