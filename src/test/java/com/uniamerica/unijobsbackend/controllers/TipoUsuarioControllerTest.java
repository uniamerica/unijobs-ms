package com.uniamerica.unijobsbackend.controllers;


import com.uniamerica.unijobsbackend.models.TipoUsuario;
import com.uniamerica.unijobsbackend.repositories.TipoUsuarioRepository;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import com.uniamerica.unijobsbackend.services.TipoUsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class TipoUsuarioControllerTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Mock
    private TipoUsuarioService tipoUsuarioService;

    @InjectMocks
    private TipoUsuarioController TestClassController;

    @DisplayName("Trazer todos os Tipos Usuarios")
    @Test
    void shouldReturnSuccess_FindAllTipoServices() {

        TipoUsuario tipoUsuario1 = new TipoUsuario(1,"admin");
        tipoUsuarioRepository.save(tipoUsuario1);

        ResponseEntity<List<TipoUsuario>> listTipoUsuarios = TestClassController.BuscarTodos();

        //then
        assertThat(listTipoUsuarios).isNotNull();
        assertThat(listTipoUsuarios.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @DisplayName("Registrar um novo Tipo Usuario")
    @Test
    void criarTipoUsuario() {

        // cenario
        TipoUsuario tipoUsuarioteste = new TipoUsuario(1,"admn");

        ResponseEntity<TipoUsuario> result = TestClassController.criarTipoUsuario(tipoUsuarioteste);

        ResponseEntity<List<TipoUsuario>> listTipoUsuarios = TestClassController.BuscarTodos();

        //then
        assertThat(listTipoUsuarios).isNotNull();

    }
    @DisplayName("Deletar um Tipo Usuario")
    @Test
    void removerTipoUsuario() {

        TipoUsuario tipoUsuarioteste = new TipoUsuario(1,"Marcia");

        ResponseEntity<TipoUsuario> cad = TestClassController.criarTipoUsuario(tipoUsuarioteste);
        tipoUsuarioRepository.save(tipoUsuarioteste);
        ResponseEntity result = TestClassController.removerTipoUsuario(1);

        assertThat(cad.getStatusCode()).isEqualTo(HttpStatus. CREATED);
    }
    @DisplayName("Atualizar um Tipo Usuario")
    @Test
    void atualiazarTipoUsuario() {
        // cenario
        TipoUsuario tipoUsuarioteste = new TipoUsuario(1,"ad");
        TipoUsuario novoTipoUsuario = new TipoUsuario("Lucas");
        tipoUsuarioRepository.save(tipoUsuarioteste);

        when(tipoUsuarioRepository.findById(any())).thenReturn(Optional.of(tipoUsuarioteste));

        Assertions.assertDoesNotThrow(() -> TestClassController.atualizarTipoUsuario(1, novoTipoUsuario));
    }
    @DisplayName("Busca Tipo Usuario por id")
    @Test
    void buscarTipoUsuario() {
        // cenario
        TipoUsuario tipoUsuarioteste = new TipoUsuario(1,"admn");
        tipoUsuarioRepository.save(tipoUsuarioteste);

        when(tipoUsuarioRepository.findById(any())).thenReturn(Optional.of(tipoUsuarioteste));

        Assertions.assertDoesNotThrow(() -> TestClassController.buscarTipoUsuario(1));
    }
}
