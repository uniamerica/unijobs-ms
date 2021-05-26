package com.uniamerica.unijobsbackend.produto;

import com.uniamerica.unijobsbackend.tipoProduto.TipoProduto;
import com.uniamerica.unijobsbackend.tipoProduto.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/produtos"})
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> visualizar(){
        return produtoService.VisualizarProduto() ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto cadastrar(@Valid @RequestBody Produto produto){
        return produtoService.CadastrarProduto(produto);
    }

    @PutMapping(path = "{id_produto}")
    @ResponseStatus(HttpStatus.OK)
    public Produto editar(@Valid @RequestBody Produto novoProduto, @PathVariable("id_produto") Integer id_produto){
        return produtoService.EditarProduto(id_produto, novoProduto);
    }

    @DeleteMapping(path = "{id_produto}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable("id_produto") Integer id_produto){
        return produtoService.DeletarProduto(id_produto);
    }

    //----------------------------------------------------------------------------------------------------
//    private RepositorioProduto repositorioProduto;
//
//    ProdutoController(RepositorioProduto repositorioProduto) {
//        this.repositorioProduto = repositorioProduto;
//    }
//    // métodos do CRUD aqui
//    @GetMapping
//    public List<Produto> visualizar(){
//        return repositorioProduto.findAll();
//    }
//
//    @GetMapping(path = {"/{id_produto}"})
//    public ResponseEntity visualizar_por_id(@PathVariable int id_produto){
//        return repositorioProduto.findById(id_produto)
//                .map(record -> ResponseEntity.ok().body(record))
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public Produto cadastrar(@RequestBody Produto produto){
//        return repositorioProduto.save(produto);
//    }
//
//    @PutMapping(value="/{id_produto}")
//    public ResponseEntity editar(@PathVariable("id_produto") int id_produto,
//                                 @RequestBody Produto produto) {
//        return repositorioProduto.findById(id_produto)
//                .map(record -> {
//                    record.setTitulo(produto.getTitulo());
//                    record.setDescricao(produto.getDescricao());
//                    record.setPreco(produto.getPreco());
//                    record.setMiniatura(produto.getMiniatura());
//                    record.setAtivo(produto.getAtivo());
//                    record.setPrazo(produto.getPrazo());
//                    Produto edita = repositorioProduto.save(record);
//                    return ResponseEntity.ok().body(edita);
//                }).orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping(path ={"/{id_produto}"})
//    public ResponseEntity <?> deletar(@PathVariable int id_produto) {
//        return repositorioProduto.findById(id_produto)
//                .map(record -> {
//                    repositorioProduto.deleteById(id_produto);
//                    return ResponseEntity.ok().build();
//                }).orElse(ResponseEntity.notFound().build());
//    }
}
