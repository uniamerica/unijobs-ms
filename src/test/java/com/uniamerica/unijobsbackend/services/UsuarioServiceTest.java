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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
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

    @BeforeEach
    public void setupMock() {
        Usuario usuario = new Usuario();
        when(usuarioRepositoryMock.findByEmail(ArgumentMatchers.anyString())).thenReturn(Optional.of(usuario));
        when(usuarioRepositoryMock.save(ArgumentMatchers.any())).thenReturn(getClass());
    }


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
        List<Usuario> before = usuarioService.index();

        Usuario usuario = new Usuario();

        usuario.setNome("Willian");
        usuario.setEmail("will@unijobs");
        usuario.setSenha("46665");
        usuario.setCelular("458489");
        usuario.setRa("4544");

        usuarioService.store(usuario);

        List<Usuario> after = usuarioService.index();
        Assertions.assertEquals(after.size(), before.size() + 1);


    }

    @Test
    void show() {
        Usuario usuario = new Usuario();
        usuario.setNome("Willian");
        usuario.setEmail("will@unijobs");
        usuario.setSenha("46665");
        usuario.setCelular("458489");
        usuario.setRa("4544");

        usuarioService.store(usuario);

        Optional<Usuario> result = usuarioService.show(usuario.getId());
        Assertions.assertTrue(result.isPresent());

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