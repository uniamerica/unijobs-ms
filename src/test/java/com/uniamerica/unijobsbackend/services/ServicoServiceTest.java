package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.Excessoes.RecursoNaoEncontradoExcessao;
import com.uniamerica.unijobsbackend.dto.ServicoDTO;
import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class ServicoServiceTest {

    @InjectMocks
    private ServicoService underTest;

    @Mock
    private ServicoRepository repository;

    @Mock
    private TipoServicoRepository tipoServicoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;


    @BeforeEach
    void setUp() {
        underTest = new ServicoService(repository, tipoServicoRepository);
    }

    private ServicoDTO mudarMiniaturaparaNova(ServicoDTO newServicoDTO){
        newServicoDTO.setMiniatura("http://res.cloudinary.com/unijobs/image/upload/v1633275994/blvw1jozgdynmrtocgzx.jpg");
        return newServicoDTO;
    }

    @Test
    void shouldFindAllServicos() {
        Page<Servico> servicos = Mockito.mock(Page.class);
        //given
        Pageable pageable = PageRequest.of(1, 10);
        // when
        when(repository.findAll(pageable)).thenReturn(servicos);
        underTest.findAll(pageable);

        //then
        Mockito.verify(repository).findAll(pageable);
    }

    @Test
    void shouldStoreServico() {

        TipoServico tiposervico = new TipoServico(1);
        Usuario usuario = new Usuario(1);

        Servico newServico = new Servico(
                1,
                "Python",
                "Aulas de Python",
                100.0,
                "http://res.cloudinary.com/unijobs/image/upload/v1633275994/blvw1jozgdynmrtocgzx.jpg",
                false,
                10,
                tiposervico,
                usuario
        );

        ServicoDTO newServicoDTO = new ServicoDTO(newServico);

        when(tipoServicoRepository.findById(any())).thenReturn(Optional.of(tiposervico));
        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(repository.save(any())).thenReturn(newServico);
        when(repository.findById(any())).thenReturn(Optional.of(newServico));

        assertThat(newServicoDTO.getId()).isEqualTo(1);
        assertThat(newServicoDTO).isNotNull();
        Assertions.assertEquals(newServicoDTO, mudarMiniaturaparaNova(underTest.store(newServico)));
        Assertions.assertEquals(newServicoDTO, mudarMiniaturaparaNova(underTest.find(newServico.getId_servico())));
    }

    @Test
    void shouldServicoStoreThrowsException() {
        // given (cenario, monta os dados)
        Servico servico = Servico.builder()
                .tipoServico(new TipoServico(1))
                .ativo(true)
                .descricao("joj")
                .titulo("teste")
                .preco(10.0)
                .miniatura("ttttt")
                .build();

        //when (Simulação)
        when(tipoServicoRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        //then (Verificação)
        assertThatThrownBy(() -> underTest.store(servico)).isInstanceOf(RecursoNaoEncontradoExcessao.class);
    }

    @Test
    void shouldDestroyServico() {
        // cenario
        Servico servico = Servico.builder()
                .tipoServico(new TipoServico(1))
                .ativo(true)
                .descricao("joj")
                .titulo("teste")
                .preco(10.0)
                .miniatura("ttttt")
                .build();

        // simulação
        when(repository.findById(servico.getId_servico())).thenReturn(java.util.Optional.of(servico));
        var result = underTest.destroy(servico.getId_servico());

        assertThat(result).isEqualTo("Serviço deletado com sucesso!");
    }

    @Test
    void shouldDestroyServicoReturnsExepctionIfNotFound() {
        // given (cenario, monta os dados)
        Servico servico = Servico.builder()
                .tipoServico(new TipoServico(1))
                .ativo(true)
                .descricao("joj")
                .titulo("teste")
                .preco(10.0)
                .miniatura("ttttt")
                .build();

        // simulação
        when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.destroy(servico.getId_servico())).isInstanceOf(RecursoNaoEncontradoExcessao.class);
    }

    @Test
    void shouldUpdateServico() {

        TipoServico tiposervico = new TipoServico(1);
        Usuario usuario = new Usuario(1);

        Servico newServico = new Servico(
                1,
                "Python",
                "Aulas de Python",
                100.0,
                "http://res.cloudinary.com/unijobs/image/upload/v1633275994/blvw1jozgdynmrtocgzx.jpg",
                false,
                10,
                tiposervico,
                usuario
        );

        Servico newServicoForUpdate = new Servico(
                1,
                "Java",
                "Aulas de Java",
                10.0,
                "http://res.cloudinary.com/unijobs/image/upload/v1633275994/blvw1jozgdynmrtocgzx.jpg",
                false,
                15,
                tiposervico,
                usuario
        );

        ServicoDTO newServicoDTO = new ServicoDTO(newServico);

        when(tipoServicoRepository.findById(any())).thenReturn(Optional.of(tiposervico));
        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(repository.save(any())).thenReturn(newServico);
        when(repository.findById(any())).thenReturn(Optional.of(newServico));

        var result = underTest.update(newServico.getId_servico(), newServicoForUpdate);

        assertThat(newServicoDTO.getId()).isEqualTo(1);
        assertThat(newServicoDTO).isNotNull();

        assertThat(result)
                .isNotNull()
                .isEqualTo(new ServicoDTO(newServico));
        assertThat(result.getId()).isEqualTo(newServico.getId_servico());
    }

    @Test
    void shouldUpdateServicoReturnExceptionIfServicoNotFound() {
        TipoServico tipoServico = new TipoServico(1);
        Servico servico = Servico.builder()
                .tipoServico(tipoServico)
                .ativo(true)
                .descricao("joj")
                .titulo("teste")
                .preco(10.0)
                .miniatura("ttttt")
                .build();

        when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.update(servico.getId_servico(), servico)).isInstanceOf(RecursoNaoEncontradoExcessao.class);
    }

    @Test
    void shouldUpdateServicoReturnExceptionIfTipoServicoNotFound() {
        TipoServico tipoServico = new TipoServico(1);
        Servico servico = Servico.builder()
                .tipoServico(tipoServico)
                .ativo(true)
                .descricao("joj")
                .titulo("teste")
                .preco(10.0)
                .miniatura("ttttt")
                .build();

        when(repository.findById(servico.getId_servico())).thenReturn(java.util.Optional.of(servico));
        when(tipoServicoRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.update(servico.getId_servico(), servico)).isInstanceOf(RecursoNaoEncontradoExcessao.class);
    }

    @Test
    void shouldFindServico() {

        TipoServico tiposervico = new TipoServico(1);
        Usuario usuario = new Usuario(1);

        Servico newServico = new Servico(
                1,
                "Python",
                "Aulas de Python",
                100.0,
                "http://res.cloudinary.com/unijobs/image/upload/v1633275994/blvw1jozgdynmrtocgzx.jpg",
                false,
                10,
                tiposervico,
                usuario
        );

        ServicoDTO newServicoDTO = new ServicoDTO(newServico);

        when(tipoServicoRepository.findById(any())).thenReturn(Optional.of(tiposervico));
        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(repository.save(any())).thenReturn(newServico);
        when(repository.findById(any())).thenReturn(Optional.of(newServico));

        var result = underTest.find(newServico.getId_servico());

        assertThat(newServicoDTO.getId()).isEqualTo(1);
        assertThat(newServicoDTO).isNotNull();
        assertThat(result)
                .isNotNull()
                .isEqualTo(new ServicoDTO(newServico));
    }

    @Test
    void shouldFindServicoReturnExceptionIfServicoNotFound() {
        TipoServico tipoServico = new TipoServico(1);
        Servico servico = Servico.builder()
                .tipoServico(tipoServico)
                .ativo(true)
                .descricao("joj")
                .titulo("teste")
                .preco(10.0)
                .miniatura("ttttt")
                .build();

        when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.find(servico.getId_servico())).isInstanceOf(RecursoNaoEncontradoExcessao.class);
    }
}