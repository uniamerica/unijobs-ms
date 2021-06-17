package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.Excessoes.RecursoNaoEncontradoExcessao;
import com.uniamerica.unijobsbackend.dto.ServicoDTO;
import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.config.web.servlet.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ServicoService {

    private final ServicoRepository repository;
    private final TipoServicoRepository tipoServicoRepository;

    public Page<ServicoDTO> findAll(Pageable pageable){
        Page<Servico> servicos = repository.findAll(pageable);
        return servicos.map(ServicoDTO::new);
    }
    public ServicoDTO store(Servico servico) {
        Integer id_tipo_servico = servico.getTipoServico().getId_tipo_servico();

        var tipoServico = tipoServicoRepository.findById(id_tipo_servico)
                .orElseThrow(
                        () -> new RecursoNaoEncontradoExcessao("Tipo Serviço não Encontrado! id:" + id_tipo_servico)
                );
        servico.setTipoServico(tipoServico);

        return new ServicoDTO(repository.save(servico));
    }

    public String destroy(Integer id) {
        repository.findById(id).orElseThrow(RecursoNaoEncontradoExcessao::new);
        repository.deleteById(id);
        return "Serviço deletado com sucesso!";
    }

    @Transactional
    public ServicoDTO update(Integer id, Servico novoServico) {
        var servico = repository.findById(id).orElseThrow(RecursoNaoEncontradoExcessao::new);

        Integer id_tipo_servico = novoServico.getTipoServico().getId_tipo_servico();
        var tipoServico = tipoServicoRepository.findById(id_tipo_servico)
                .orElseThrow(
                        () -> new RecursoNaoEncontradoExcessao("Tipo Serviço não Encontrado! id:" + id_tipo_servico)
                );

        servico.setTitulo(novoServico.getTitulo());
        servico.setDescricao(novoServico.getDescricao());
        servico.setMiniatura(novoServico.getMiniatura());
        servico.setPrazo(novoServico.getPrazo());
        servico.setPreco(novoServico.getPreco());
        servico.setTipoServico(tipoServico);
        servico.setAtivo(novoServico.isAtivo());

        return new ServicoDTO(servico);
    }

    public ServicoDTO find(Integer id) {
        return new ServicoDTO(repository.findById(id).orElseThrow(RecursoNaoEncontradoExcessao::new));
    }
}
