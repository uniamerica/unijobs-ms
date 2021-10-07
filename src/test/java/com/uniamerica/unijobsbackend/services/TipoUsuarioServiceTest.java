package com.uniamerica.unijobsbackend.services;


import com.uniamerica.unijobsbackend.models.TipoUsuario;
import com.uniamerica.unijobsbackend.repositories.TipoUsuarioRepository;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class TipoUsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;
    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private TipoUsuarioService tipoUsuarioClass;


    @DisplayName("Trazer todos os Tipos Usuarios")
    @Test
    void shouldReturnSuccess_FindAllTipoUsuarios() {

        TipoUsuario tipoUsuario1 = new TipoUsuario(1,"Administrador");
        tipoUsuarioRepository.save(tipoUsuario1);

        var listTipoUsuarios = tipoUsuarioClass.index();

        //then
        assertThat(listTipoUsuarios.contains(tipoUsuario1));
    }

    @DisplayName("Registrar um novo Tipo usuario")
    @Test
    void store() {

        // cenario
        TipoUsuario tipoUsuarioteste = new TipoUsuario(1,"USER_PERSON");
        var result = tipoUsuarioClass.store(tipoUsuarioteste);

        var listTipoUsuarios = tipoUsuarioClass.index();

        //then
        assertThat(listTipoUsuarios.contains(tipoUsuarioteste));

    }
    @DisplayName("Deletar um Tipo Usuario")
    @Test
    void Destroy() {
        // cenario
        TipoUsuario tipoUsuarioteste = new TipoUsuario(1,"universidade_user");

        when(tipoUsuarioClass.store(tipoUsuarioteste)).thenReturn(tipoUsuarioteste);

        var result = tipoUsuarioClass.destroy(1);

        assertThat(result).isEqualTo("Tipo Usuario deletado com sucesso!");

    }
    @DisplayName("Atualizar um Tipo usuario")
    @Test
    void update() {
        // cenario
        TipoUsuario tipoUsuarioteste = new TipoUsuario(1,"seguranca");
        TipoUsuario novoTipoUsuario = new TipoUsuario("secretaria");
        tipoUsuarioRepository.save(tipoUsuarioteste);

        when(tipoUsuarioRepository.findById(any())).thenReturn(Optional.of(tipoUsuarioteste));

        Assertions.assertDoesNotThrow(() -> tipoUsuarioClass.update(novoTipoUsuario));
    }
    @DisplayName("Busca Tipo Usuario por id")
    @Test
    void findById() {
        // cenario
        TipoUsuario tipoUsuarioteste = new TipoUsuario(1,"ad");
        tipoUsuarioRepository.save(tipoUsuarioteste);

        when(tipoUsuarioRepository.findById(any())).thenReturn(Optional.of(tipoUsuarioteste));

        Assertions.assertDoesNotThrow(() -> tipoUsuarioClass.show(1));
    }


}
