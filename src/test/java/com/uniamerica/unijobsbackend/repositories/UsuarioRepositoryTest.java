package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.models.Usuario;

import com.uniamerica.unijobsbackend.services.UsuarioService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findUsuarioServicoByNomeShouldReturnUsuarioServico(){
        //given
        String email = "liocroons@2unijobs.com";
        Usuario usuario = new Usuario(null, email, "1234","lio", "450000", "1111", null);
        underTest.save(usuario);

        //when
        var exists = underTest.findByEmail(email);

        //then
        assertThat(exists).isPresent();
    }



}
