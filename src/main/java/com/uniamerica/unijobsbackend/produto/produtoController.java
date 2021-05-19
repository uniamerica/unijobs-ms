package com.uniamerica.unijobsbackend.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/produtos"})
public class produtoController {
    private repositorioProduto repositorioProduto;

    produtoController(repositorioProduto repositorioProduto) {
        this.repositorioProduto = repositorioProduto;
    }
    // métodos do CRUD aqui
    @GetMapping
    public List<Produto> visualizar(){
        return repositorioProduto.findAll();
    }

    @GetMapping(path = {"/{id_produto}"})
    public ResponseEntity visualizar_por_id(@PathVariable int id_produto){
        return repositorioProduto.findById(id_produto)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto){
        return repositorioProduto.save(produto);
    }

    @PutMapping(value="/{id_produto}")
    public ResponseEntity editar(@PathVariable("id_produto") int id_produto,
                                 @RequestBody Produto produto) {
        return repositorioProduto.findById(id_produto)
                .map(record -> {
                    record.setTitulo(produto.getTitulo());
                    record.setDescricao(produto.getDescricao());
                    record.setPreco(produto.getPreco());
                    record.setMiniatura(produto.getMiniatura());
                    record.setAtivo(produto.getAtivo());
                    record.setPrazo(produto.getPrazo());
                    Produto edita = repositorioProduto.save(record);
                    return ResponseEntity.ok().body(edita);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id_produto}"})
    public ResponseEntity <?> deletar(@PathVariable int id_produto) {
        return repositorioProduto.findById(id_produto)
                .map(record -> {
                    repositorioProduto.deleteById(id_produto);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
