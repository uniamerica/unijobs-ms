package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.TipoServico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TipoServicoRepositoryTest {

    @Autowired
    private TipoServicoRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findTipoServicoByNomeShouldReturnTipoServico(){
        String nome = "Manutencao";
        TipoServico tipoServico = new TipoServico(nome, "manutencao em computadores");
        underTest.save(tipoServico);

        var exists = underTest.findTipoServicoByNome(nome);

        assertThat(exists).contains(tipoServico);
    }

    @Test
    void findTipoServicoByNomeShouldNotBeFound(){
        String nome = "Jorge";

        var exists = underTest.findTipoServicoByNome("Jorge");

        assertThat(exists).isNotPresent();
    }
}