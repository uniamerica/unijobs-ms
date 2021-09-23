package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.UsuarioFake;
import com.uniamerica.unijobsbackend.models.TipoUsuario;
import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.repositories.TipoUsuarioRepository;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @Mock
    private TipoUsuarioRepository tipoUsuarioRepositoryMock;

//    @BeforeEach
//    void setupMock() {
//        UsuarioFake usuarioFake = new UsuarioFake();
//        when(usuarioRepositoryMock.findByEmail(ArgumentMatchers.anyString())).thenReturn(Optional.of(usuarioFake.getInstance()));
//        when(usuarioRepositoryMock.save(ArgumentMatchers.any())).thenReturn(getClass(getClass()));
//    }


    @Test
    @DisplayName("o administrador deve cadastrar ou registar um novo usuario")
    void store() {
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setId(1);
        Usuario usuario = new Usuario(
                0,
                "email teste",
                "senha teste 1",
                "Lio",
                "45999999999",
                "503090",
                tipoUsuario
        );

        when(tipoUsuarioRepositoryMock.findById(any())).thenReturn(Optional.of(tipoUsuario));

        Assertions.assertDoesNotThrow(()->{usuarioService.store(usuario);});
    }

    @Test
    void index() {
    }

    @Test
    void show() {
    }

    @Test
    void update() {
    }

    @Test
    void destroy() {
    }
}