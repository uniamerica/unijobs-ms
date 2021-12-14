package com.uniamerica.unijobsbackend.controllers;

import com.uniamerica.unijobsbackend.dto.ProdutoDTO;
import com.uniamerica.unijobsbackend.dto.input.NovoProdutoDTO;
import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.services.ProdutoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping({"/produtos"})
@SecurityRequirement(name = "bearerAuth")
@OpenAPIDefinition
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    private final ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "Retorna uma lista de Produtos")
    public Page<ProdutoDTO> visualizar(Pageable pageable) {
        return produtoService.findAll(pageable)
                .map(this::toProdutoDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastra um Produto.")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto cadastrar(@Valid @ModelAttribute NovoProdutoDTO produtoDTO) {
        return produtoService.create(produtoDTO.converteModelo());
    }

    @PutMapping(path = "{id_produto}")
    @Operation(summary = "Edita um Produto")
    @ResponseStatus(HttpStatus.OK)
    public Produto editar(@Valid @RequestBody Produto novoProduto, @PathVariable("id_produto") Integer idProduto) {
        return produtoService.update(idProduto, novoProduto);
    }

    @DeleteMapping(path = "{id_produto}")
    @Operation(summary = "Deleta um Produto.")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable("id_produto") Integer idProduto) {
        return produtoService.delete(idProduto);
    }

    private ProdutoDTO toProdutoDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    @GetMapping(path = "{idProduto:[1-9]+}")
    @Operation(summary = "Busca um Produto")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDTO buscaProdutoPorId(@PathVariable Integer idProduto) {
        return toProdutoDTO(produtoService.findById(idProduto));
    }

    @GetMapping(path = "{search}/search")
    @Operation(summary = "Busca um Produto")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<Produto>> buscaProdutos(@PathVariable String search, Pageable pageable) {
        return ok(produtoService.findBySearch(search, pageable));
    }
}
