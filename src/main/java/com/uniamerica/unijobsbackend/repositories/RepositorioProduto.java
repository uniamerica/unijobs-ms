package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProduto extends JpaRepository<Produto, Integer> {
}
