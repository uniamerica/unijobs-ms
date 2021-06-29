package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.Excessoes.RecursoNaoEncontradoExcessao;
import com.uniamerica.unijobsbackend.dto.ServicoDTO;
import com.uniamerica.unijobsbackend.dto.TipoServicoDTO;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoServicoService {

    private final TipoServicoRepository tipoServicoRepository;

    public TipoServicoService(TipoServicoRepository tipoServicoRepository) {
        this.tipoServicoRepository = tipoServicoRepository;
    }

    public List<TipoServicoDTO> listarTiposServicos(){
        return tipoServicoRepository.findAll().stream().map(TipoServicoDTO::new).collect(Collectors.toList());
    }

    public TipoServicoDTO novoTipoServico(TipoServico tipoServico) {
        return new TipoServicoDTO(tipoServicoRepository.save(tipoServico));
    }

    public String deletarTipoServico(Integer id) {
        boolean existe = tipoServicoRepository.existsById(id);
        if(!existe){
            throw new RecursoNaoEncontradoExcessao("Tipo de Serviço não Existe. id: " + id);
        }
        tipoServicoRepository.deleteById(id);
        return "Tipo Serviço deletado com sucesso!";
    }

    @Transactional
    public TipoServicoDTO atualizarTipoServico(Integer id, TipoServico novoTipoServico) {
        TipoServico tipoServico = tipoServicoRepository.findById(id)
                .orElseThrow(
                        () -> new RecursoNaoEncontradoExcessao("Tipo de Serviço não Existe. id: " + id)
                );
            tipoServico.setNome(novoTipoServico.getNome());
            tipoServico.setDescricao(novoTipoServico.getDescricao());
        return new TipoServicoDTO(tipoServico);
    }

    public List<ServicoDTO> servicosByTipoServicos(Integer id_tipo_servico){
        TipoServico tipoServico = tipoServicoRepository.findById(id_tipo_servico)
                .orElseThrow(
                        () -> new RecursoNaoEncontradoExcessao("Tipo Serviço não Encontrado! id:" + id_tipo_servico)
                );
        return tipoServico.getServico().stream().map(ServicoDTO::new).collect(Collectors.toList());
    }
}
