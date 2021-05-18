package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.dto.ServicoDTO;
import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public Page<ServicoDTO> findAll(Pageable pageable){
        Page<Servico> servicos = repository.findAll(pageable);
        return servicos.map(x -> new ServicoDTO(x));
    }
}
