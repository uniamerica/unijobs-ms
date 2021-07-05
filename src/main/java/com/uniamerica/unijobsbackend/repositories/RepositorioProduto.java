package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.models.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioProduto extends JpaRepository<Produto, Integer> {
    List<Produto> findTop5ByOrderByUpdatedAtDesc();

    List<Produto> findByTipoProduto(TipoProduto tipoProduto);
}
