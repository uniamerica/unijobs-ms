package com.uniamerica.unijobsbackend.controllers;

import com.uniamerica.unijobsbackend.dto.ItensDTO;
import com.uniamerica.unijobsbackend.services.ItensService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/itens"})
@OpenAPIDefinition
public class ItensController {
    private final ItensService service;

    public ItensController(ItensService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ItensDTO>> recentemente_adicionados(){
        return ResponseEntity.ok(service.recentemente_adicionados());
    }

}
