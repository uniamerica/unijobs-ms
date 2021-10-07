package com.uniamerica.unijobsbackend.services;

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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @Mock
    private TipoUsuarioRepository tipoUsuarioRepositoryMock;

    @BeforeEach
    void setup() {
        Usuario usuario = new Usuario();
        when(usuarioRepositoryMock.findByEmail(ArgumentMatchers.anyString())).thenReturn(Optional.of(usuario));
        when(usuarioRepositoryMock.save(ArgumentMatchers.any())).thenReturn(getClass());
    }



    @Test
    @DisplayName("O adiministrador do usuario deve eliminar o usuario")
    void destroy() {
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setId(2);

        Usuario usuario = new Usuario(
                0,
                "liocroons@unijobs.com",
                "test123",
                "Lio croons",
                "12333455",
                "503090",
                tipoUsuario
        );

        when(tipoUsuarioRepositoryMock.findById(any())).thenReturn(Optional.of(tipoUsuario));

        Assertions.assertDoesNotThrow(()->{usuarioService.destroy(usuario.getId());});
    }
}