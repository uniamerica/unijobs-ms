package com.uniamerica.unijobsbackend.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProduto extends JpaRepository<Produto, Integer> {
}
