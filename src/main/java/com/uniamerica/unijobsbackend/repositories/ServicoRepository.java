package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    List<Servico> findTop5ByOrderByUpdatedAtDesc();
    List<Servico> findByTipoServico(TipoServico tipoServico);
}
