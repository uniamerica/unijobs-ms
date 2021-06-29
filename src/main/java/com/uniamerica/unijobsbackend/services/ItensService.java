package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.Excessoes.RecursoNaoEncontradoExcessao;
import com.uniamerica.unijobsbackend.dto.ItensDTO;
import com.uniamerica.unijobsbackend.dto.ServicoDTO;
import com.uniamerica.unijobsbackend.models.*;
import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.repositories.*;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.config.web.servlet.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class ItensService {

    private final ServicoRepository servicoRepository;
    private final TipoServicoRepository tipoServicoRepository;
    private final RepositorioProduto produtoRepository;
    private final RepositorioTipoProduto tipoProdutoRepository;

    public List<ItensDTO> recentemente_adicionados(){
        var produtos = produtoRepository.findTop5ByOrderByUpdatedAtDesc();
        var servicos = servicoRepository.findTop5ByOrderByUpdatedAtDesc();

        return Stream.concat(produtos.stream().map(ItensDTO::new), servicos.stream().map(ItensDTO::new)).collect(Collectors.toList());
    }
}
