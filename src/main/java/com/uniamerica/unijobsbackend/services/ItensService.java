package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.dto.ItensDTO;
import com.uniamerica.unijobsbackend.repositories.RepositorioProduto;
import com.uniamerica.unijobsbackend.repositories.RepositorioTipoProduto;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
