package com.uniamerica.unijobsbackend.tipoProduto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioTipoProduto extends JpaRepository<TipoProduto, Integer> {
    Optional<TipoProduto> findTipoProdutoByNome(String nome);
}
