package com.uniamerica.unijobsbackend.controllers;

import com.uniamerica.unijobsbackend.dto.ServicoDTO;
import com.uniamerica.unijobsbackend.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
    
    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public ResponseEntity<Page<ServicoDTO>> findAll(Pageable pageable){
        Page<ServicoDTO> lista = servicoService.findAll(pageable);
        return ResponseEntity.ok(lista);
    }
}
