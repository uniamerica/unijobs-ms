package com.uniamerica.unijobsbackend.tipoServico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoServicoRepository extends JpaRepository<TipoServico, Integer> {

    Optional<TipoServico> findTipoServicoByNome(String nome);
}
