package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

}