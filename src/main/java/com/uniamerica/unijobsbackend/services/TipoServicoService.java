package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.Excessoes.RecursoNaoEncontradoExcessao;
import com.uniamerica.unijobsbackend.dto.ServicoDTO;
import com.uniamerica.unijobsbackend.dto.TipoServicoDTO;
import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoServicoService {

    private final TipoServicoRepository tipoServicoRepository;

    private final ServicoRepository servicoRepository;

    public List<TipoServicoDTO> findAll() {
        return tipoServicoRepository.findAll().stream().map(TipoServicoDTO::new).collect(Collectors.toList());
    }

    public TipoServico save(TipoServico tipoServico) {
        return tipoServicoRepository.save(tipoServico);
    }

    public String deletarTipoServico(Integer id) {
        tipoServicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcessao("Tipo de Serviço não Existe. id: " + id));

        tipoServicoRepository.deleteById(id);
        return "Tipo Serviço deletado com sucesso!";
    }

    public TipoServicoDTO update(Integer id, TipoServico novoTipoServico) {
        TipoServico tipoServico = tipoServicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcessao("Tipo de Serviço não Existe. id: " + id));

        tipoServico.setNome(novoTipoServico.getNome());
        tipoServico.setDescricao(novoTipoServico.getDescricao());
        return new TipoServicoDTO(tipoServico);
    }

    public List<ServicoDTO> findByServiceType(Integer idTipoServico) {
        TipoServico tipoServico = tipoServicoRepository.findById(idTipoServico)
                .orElseThrow(() -> new RecursoNaoEncontradoExcessao("Tipo Serviço não Encontrado! id:" + idTipoServico));

        List<Servico> servicos = servicoRepository.findByTipoServico(tipoServico);
        return servicos.stream().map(ServicoDTO::new).collect(Collectors.toList());
    }
}
