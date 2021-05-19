package com.uniamerica.unijobsbackend.tipoProduto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositorioTipoProduto extends JpaRepository<tipoProduto, Integer> {}
