package com.uniamerica.unijobsbackend.controllers;

import com.uniamerica.unijobsbackend.dto.ProdutoDTO;
import com.uniamerica.unijobsbackend.dto.ServicoDTO;
import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.dto.input.NovoProdutoDTO;
import com.uniamerica.unijobsbackend.services.ProdutoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
/*
    @GetMapping
    @Operation(summary = "Retorna uma lista de Produtos")
    public Page<ProdutoDTO> visualizar(){
        return produtoService.VisualizarProduto()
                .map(this::toProdutoDTO);
    }*/

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
    public Produto deletar(@PathVariable("id_produto") Integer id_produto){
        return produtoService.DeletarProduto(id_produto);
    }

    private ProdutoDTO toProdutoDTO(Produto produto){
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    @GetMapping(path = "{id_produto}")
    @Operation(summary = "Busca um Produto")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDTO buscaProdutoPorId(@PathVariable("id_produto") Integer id_produto){
        return toProdutoDTO(produtoService.BuscarProduto(id_produto));
    }
}
