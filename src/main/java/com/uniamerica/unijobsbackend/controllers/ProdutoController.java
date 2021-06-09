package com.uniamerica.unijobsbackend.controllers;

import com.uniamerica.unijobsbackend.dto.ListaProdutoDTO;
import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.dto.NovoProdutoDTO;
import com.uniamerica.unijobsbackend.services.ProdutoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/produtos"})
@SecurityRequirement(name = "bearerAuth")
@OpenAPIDefinition
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "Retorna uma lista de Produtos")
    public List<ListaProdutoDTO> visualizar(){
        return produtoService.VisualizarProduto()
                .stream()
                .map(this::toListaProdutoDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "Cadastra um Produto.")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto cadastrar(@Valid @RequestBody NovoProdutoDTO produto){
        return produtoService.CadastrarProduto(produto.converteModelo());
    }

    @PutMapping(path = "{id_produto}")
    @Operation(summary = "Edita um Produto")
    @ResponseStatus(HttpStatus.OK)
    public Produto editar(@Valid @RequestBody Produto novoProduto, @PathVariable("id_produto") Integer id_produto){
        return produtoService.EditarProduto(id_produto, novoProduto);
    }

    @DeleteMapping(path = "{id_produto}")
    @Operation(summary = "Deleta um Produto.")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable("id_produto") Integer id_produto){
        return produtoService.DeletarProduto(id_produto);
    }

    private ListaProdutoDTO toListaProdutoDTO(Produto produto){
        return modelMapper.map(produto, ListaProdutoDTO.class);
    }
}
