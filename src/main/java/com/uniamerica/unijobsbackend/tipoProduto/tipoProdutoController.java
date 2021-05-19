package com.uniamerica.unijobsbackend.tipoProduto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/tipos_produtos"})
public class tipoProdutoController {
    private repositorioTipoProduto repositorioTipoProduto;

    tipoProdutoController(repositorioTipoProduto repositorioTipoProduto) {
        this.repositorioTipoProduto = repositorioTipoProduto;
    }
    // métodos do CRUD aqui
    @GetMapping
    public List<tipoProduto> visualizar(){
        return repositorioTipoProduto.findAll();
    }

    @GetMapping(path = {"/{id_tipoProduto}"})
    public ResponseEntity visualizar_por_id(@PathVariable int id_tipoProduto){
        return repositorioTipoProduto.findById(id_tipoProduto)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public tipoProduto cadastrar(@RequestBody tipoProduto tipoProduto){
        return repositorioTipoProduto.save(tipoProduto);
    }

    @PutMapping(value="/{id_tipoProduto}")
    public ResponseEntity editar(@PathVariable("id_tipoProduto") int id_tipoProduto,
                                 @RequestBody tipoProduto tipoProduto) {
        return repositorioTipoProduto.findById(id_tipoProduto)
                .map(record -> {
                    record.setNome(tipoProduto.getNome());
                    record.setDescricao(tipoProduto.getDescricao());
                    tipoProduto edita = repositorioTipoProduto.save(record);
                    return ResponseEntity.ok().body(edita);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id_tipoProduto}"})
    public ResponseEntity <?> deletar(@PathVariable int id_tipoProduto) {
        return repositorioTipoProduto.findById(id_tipoProduto)
                .map(record -> {
                    repositorioTipoProduto.deleteById(id_tipoProduto);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
