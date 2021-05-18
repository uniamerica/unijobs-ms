package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TipoServicoService {

    @Autowired
    private final TipoServicoRepository tipoServicoRepository;

    public TipoServicoService(TipoServicoRepository tipoServicoRepository) {
        this.tipoServicoRepository = tipoServicoRepository;
    }

    public List<TipoServico> listarTiposServicos(){
        return tipoServicoRepository.findAll();
    }

    public TipoServico novoTipoServico(TipoServico tipoServico) {
        validacaoNome(tipoServico.getNome());
        return tipoServicoRepository.save(tipoServico);
    }

    public String deletarTipoServico(Integer id) {
        boolean existe = tipoServicoRepository.existsById(id);
        if(!existe){
            throw new IllegalStateException("Tipo de Serviço não Existe. id: " + id);
        }
        tipoServicoRepository.deleteById(id);
        return "Tipo Serviço deletado com sucesso!";
    }

    @Transactional
    public TipoServico atualizarTipoServico(Integer id, TipoServico novoTipoServico) {
        TipoServico tipoServico = tipoServicoRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Tipo de Serviço não Existe. id: " + id)
                );
        if(novoTipoServico.getNome() != null && !Objects.equals(tipoServico.getNome(), novoTipoServico.getNome())){
            validacaoNome(novoTipoServico.getNome());
            tipoServico.setNome(novoTipoServico.getNome());
        }
        if (novoTipoServico.getDescricao() != null && !Objects.equals(tipoServico.getDescricao(), novoTipoServico.getDescricao())){
            tipoServico.setDescricao(novoTipoServico.getDescricao());
        }
        return tipoServico;
    }


    public void validacaoNome(String nome){
        Optional<TipoServico> tipoServicoByNome = tipoServicoRepository.findTipoServicoByNome(nome);
        if(tipoServicoByNome.isPresent()){
            throw new IllegalStateException("Tipo de Servico Já Existente");
        }
    }
}
