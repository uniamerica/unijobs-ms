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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public List<Produto> VisualizarProduto(){
        return repositorioProduto.findAll();
    }

    public Produto CadastrarProduto(Produto produto) {


        try{
            Integer id_tipo_produto = produto.getTipoProduto().getId_tipo_produto();
            Integer id_usuario = produto.getUsuario().getId();
        }
        catch(Error error){
            new RecursoNaoEncontradoExcessao();
        }

        Integer id_tipo_produto = produto.getTipoProduto().getId_tipo_produto();
        Integer id_usuario = produto.getUsuario().getId();

        TipoProduto tipo = repositorioTipoProduto.findById(id_tipo_produto)
          .orElseThrow(
            () -> new RecursoNaoEncontradoExcessao("Categoria não Encontrada! id:" + id_tipo_produto)
          );

        try{
            var usuario = usuarioRepository.findById(id_usuario);
        } catch(NullPointerException nullPointerException) {
            return null;
        }

        String minituraUrl = uploadMiniatura(produto.getMiniatura());
        produto.setMiniatura(minituraUrl);

        return repositorioProduto.save(produto);
    }

    public Produto DeletarProduto(Integer id) {
        Produto produto1 = repositorioProduto.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcessao("Produto não Existe. id: " + id));
        repositorioProduto.deleteById(id);
        return produto1;
        }

    @Transactional
    public Produto EditarProduto(Integer id, Produto novoProduto) {
        Produto produto1 = repositorioProduto.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcessao("Produto não Existe. id: " + id));
        novoProduto.setId_produto(produto1.getId_produto());
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
        Produto p = repositorioProduto.findById(id)
                .orElseThrow( () -> new RecursoNaoEncontradoExcessao("Produto não Existe. id: " + id) );
        return p;
    }

    private String uploadMiniatura(String miniatura) {
        Cloudinary cloudinary = CloudinarySingleton.getCloudinary();
        File file = new File(miniatura);
        try {
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            return (String) uploadResult.get("url");
        } catch (NullPointerException | IOException e) {
            return "miniatura não encontrada";
        }
    }
}
