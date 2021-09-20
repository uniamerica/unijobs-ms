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
        //given
        String nome = "Manutencao";
        TipoServico tipoServico = new TipoServico(nome, "manutencao em computadores");
        underTest.save(tipoServico);

        //when
        var exists = underTest.findTipoServicoByNome(nome);

        //then
        assertThat(exists).contains(tipoServico);
    }

    @Test
    void findTipoServicoByNomeShouldNotBeFound(){
        //given
        String nome = "Jorge";

        //when
        var exists = underTest.findTipoServicoByNome("Jorge");

        //then
        assertThat(exists).isNotPresent();
    }
}