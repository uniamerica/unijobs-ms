package com.uniamerica.unijobsbackend.tipoProduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/tipos_produtos"})
public class TipoProdutoController {
    @Autowired
    private TipoProdutoService tipoProdutoService;

    @GetMapping
    public List<TipoProduto> visualizar(){
        return tipoProdutoService.VisualizarTipoProduto() ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoProduto cadastrar(@Valid @RequestBody TipoProduto tipoProduto){
        return tipoProdutoService.CadastrarTipoProduto(tipoProduto);
    }

    @PutMapping(path = "{id_tipoProduto}")
    @ResponseStatus(HttpStatus.OK)
    public TipoProduto editar(@Valid @RequestBody TipoProduto novoTipoProduto, @PathVariable("id_tipoProduto") Integer id_tipoProduto){
        return tipoProdutoService.EditarTipoProduto(id_tipoProduto, novoTipoProduto);
    }

    @DeleteMapping(path = "{id_tipoProduto}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable("id_tipoProduto") Integer id_tipoProduto){
        return tipoProdutoService.DeletarTipoProduto(id_tipoProduto);
    }

    //-------------------------------------------------------------------------------------------------

//    TipoProdutoController(RepositorioTipoProduto repositorioTipoProduto) {
//        this.repositorioTipoProduto = repositorioTipoProduto;
//    }
//    // métodos do CRUD aqui
//    @GetMapping
//    public List<TipoProduto> visualizar(){
//        return RepositorioTipoProduto.findAll();
//    }
//
//    @GetMapping(path = {"/{id_tipoProduto}"})
//    public ResponseEntity visualizar_por_id(@PathVariable int id_tipoProduto){
//        return RepositorioTipoProduto.findById(id_tipoProduto)
//                .map(record -> ResponseEntity.ok().body(record))
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public TipoProduto cadastrar(@RequestBody TipoProduto tipoProduto){
//        return RepositorioTipoProduto.save(tipoProduto);
//    }
//
//    @PutMapping(value="/{id_tipoProduto}")
//    public ResponseEntity editar(@PathVariable("id_tipoProduto") int id_tipoProduto,
//                                 @RequestBody TipoProduto tipoProduto) {
//        return RepositorioTipoProduto.findById(id_tipoProduto)
//                .map(record -> {
//                    record.setNome(tipoProduto.getNome());
//                    record.setDescricao(tipoProduto.getDescricao());
//                    TipoProduto edita = RepositorioTipoProduto.save(record);
//                    return ResponseEntity.ok().body(edita);
//                }).orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping(path ={"/{id_tipoProduto}"})
//    public ResponseEntity <?> deletar(@PathVariable int id_tipoProduto) {
//        return RepositorioTipoProduto.findById(id_tipoProduto)
//                .map(record -> {
//                    RepositorioTipoProduto.deleteById(id_tipoProduto);
//                    return ResponseEntity.ok().build();
//                }).orElse(ResponseEntity.notFound().build());
//    }
}
