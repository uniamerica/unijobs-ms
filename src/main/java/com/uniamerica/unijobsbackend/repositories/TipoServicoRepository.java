package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoServicoRepository extends JpaRepository<TipoServico, Integer> {

    Optional<TipoServico> findTipoServicoByNome(String nome);
}
