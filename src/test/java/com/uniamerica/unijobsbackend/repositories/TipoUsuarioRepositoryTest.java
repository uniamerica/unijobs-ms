package com.uniamerica.unijobsbackend.repositories;
import com.uniamerica.unijobsbackend.models.TipoUsuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class TipoUsuarioRepositoryTest {

    @Autowired
    private TipoUsuarioRepository underTest;


    @Test
    void store(){
        //given
//        String nome = "Manutencao";
        TipoUsuario tipoUsuario = new TipoUsuario(1, "liocroons");

        TipoUsuario store = underTest.save(tipoUsuario);

        Assertions.assertEquals(store, tipoUsuario );
    }

    @Test
    void destroy(){
        //given
//        String nome = "Manutencao";
        TipoUsuario tipoUsuario = new TipoUsuario(1, "liocroons");

        TipoUsuario store = underTest.save(tipoUsuario);
        underTest.deleteById(1);

        Assertions.assertNotEquals(underTest.findById(1),tipoUsuario);
        Assertions.assertEquals(store, tipoUsuario );
    }

    @Test
    void findById(){
        //given
//        String nome = "Manutencao";
        TipoUsuario tipoUsuario = new TipoUsuario(1, "liocroons");

        TipoUsuario store = underTest.save(tipoUsuario);

        var exists = underTest.findById(1);

        Assertions.assertEquals(store, tipoUsuario );
        assertThat(exists).isPresent();
    }


}

