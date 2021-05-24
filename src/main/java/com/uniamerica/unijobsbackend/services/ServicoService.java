package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.dto.ServicoDTO;
import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public Page<ServicoDTO> findAll(Pageable pageable){
        Page<Servico> servicos = repository.findAll(pageable);
        return servicos.map(x -> new ServicoDTO(x));
    }

    public Servico store(Servico servico) {
        return repository.save(servico);
    }

    public String destroy(Integer id) {
        boolean existe = repository.existsById(id);
        if(!existe){
            throw new IllegalStateException("Serviço não Existe. id: " + id);
        }
        repository.deleteById(id);
        return "Serviço deletado com sucesso!";
    }

    @Transactional
    public Servico update(Integer id, Servico novoServico) {
        Servico servico = repository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Serviço não Existe. id: " + id)
                );
        if(novoServico.getTitulo() != null && !Objects.equals(servico.getTitulo(), novoServico.getTitulo())){
            servico.setTitulo(novoServico.getTitulo());
        }
        if (novoServico.getDescricao() != null && !Objects.equals(servico.getDescricao(), novoServico.getDescricao())){
            servico.setDescricao(novoServico.getDescricao());
        }
        return servico;
    }
}
