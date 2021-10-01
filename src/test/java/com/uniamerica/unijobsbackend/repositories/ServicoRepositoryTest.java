package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.models.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
class ServicoRepositoryTest {

    @Autowired
    private ServicoRepository underTest;

    @Mock
    private TipoServico tipoServico;

    @Mock
    private TipoServicoRepository tipoServicoRepository;

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByTipoServico(){

        TipoServico tipo = new TipoServico(1);

        Usuario usuario = new Usuario(1);

        //given
        TipoServico tipoServicoteste = new TipoServico(1,"Manutenção", "manutencao em computadores");
        tipoServicoRepository.save(tipoServicoteste);

        Servico teste = new Servico(
                0,
                "Titulo teste",
                "descricao teste",
                12.0,
                "Miniatura teste",
                false,
                28,
                tipo,
                usuario
        );

        when(tipoServicoRepository.findById(any())).thenReturn(Optional.of(tipo));
        when(usuarioRepositoryMock.findById(any())).thenReturn(Optional.of(usuario));

        Assertions.assertDoesNotThrow(() -> underTest.findByTipoServico(tipoServicoteste));

    }

    /*@Test
    void findTipoServicoByNomeShouldNotBeFound(){
        //given
        String nome = "Jorge";

        //when
        var exists = underTest.findTipoServicoByNome("Jorge");

        //then
        assertThat(exists).isNotPresent();
    }*/
}