package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.Excessoes.RecursoNaoEncontradoExcessao;
import com.uniamerica.unijobsbackend.dto.ServicoDTO;
import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class ServicoServiceTest {

    @Mock
    private ServicoRepository repository;
    @Mock
    private TipoServicoRepository tipoServicoRepository;

    private ServicoService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ServicoService(repository, tipoServicoRepository);
    }

    @Test
    void shouldFindAllServicos() {
        Page<Servico> servicos = Mockito.mock(Page.class);
        //given
        Pageable pageable = PageRequest.of(1, 10);
        // when
        Mockito.when(repository.findAll(pageable)).thenReturn(servicos);
        underTest.findAll(pageable);

        //then
        Mockito.verify(repository).findAll(pageable);
    }

    @Test
    void shouldStoreServico() {
        // given (Cenario, dados de entrada)
        TipoServico tipoServico = new TipoServico(1);

        Servico servico = Servico.builder()
                .tipoServico(tipoServico)
                .ativo(true)
                .descricao("joj")
                .titulo("teste")
                .preco(10.0)
                .miniatura("ttttt")
                .build();

        //when (Simulação)
        Mockito.when(repository.save(servico)).thenAnswer(service -> {
            Servico servicoSalvo = service.getArgument(0);
            servicoSalvo.setId_servico(1);
            return servicoSalvo;
        });
        Mockito.when(tipoServicoRepository.findById(tipoServico.getId_tipo_servico())).thenReturn(java.util.Optional.of(tipoServico));
        // Ação
        var result = underTest.store(servico);

        //then (Verificação)
        assertThat(result.getId_servico()).isEqualTo(1);
        assertThat(result).isNotNull();
        assertThat(result.getDescricao()).isEqualTo("joj");
//        assertThat(result).isEqualTo();
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
        Mockito.when(tipoServicoRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

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
        Mockito.when(repository.findById(servico.getId_servico())).thenReturn(java.util.Optional.of(servico));
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
        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.destroy(servico.getId_servico())).isInstanceOf(RecursoNaoEncontradoExcessao.class);
    }

    @Test
    void shouldUpdateServico() {
        TipoServico tipoServico = new TipoServico(1);
        Servico servico = Servico.builder()
            .tipoServico(tipoServico)
            .ativo(true)
            .descricao("joj")
            .titulo("teste")
            .preco(10.0)
            .miniatura("ttttt")
            .build();

        Mockito.when(repository.findById(servico.getId_servico())).thenReturn(java.util.Optional.of(servico));
        Mockito.when(tipoServicoRepository.findById(tipoServico.getId_tipo_servico())).thenReturn(java.util.Optional.of(tipoServico));

        var result = underTest.update(servico.getId_servico(), servico);

        assertThat(result)
                .isNotNull()
                .isEqualTo(new ServicoDTO(servico));
        assertThat(result.getId_servico()).isEqualTo(servico.getId_servico());
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

        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

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

        Mockito.when(repository.findById(servico.getId_servico())).thenReturn(java.util.Optional.of(servico));
        Mockito.when(tipoServicoRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.update(servico.getId_servico(), servico)).isInstanceOf(RecursoNaoEncontradoExcessao.class);
    }

    @Test
    void shouldFindServico() {
        TipoServico tipoServico = new TipoServico(1);
        Servico servico = Servico.builder()
                .tipoServico(tipoServico)
                .ativo(true)
                .descricao("joj")
                .titulo("teste")
                .preco(10.0)
                .miniatura("ttttt")
                .build();

        Mockito.when(repository.findById(servico.getId_servico())).thenReturn(java.util.Optional.of(servico));

        var result = underTest.find(servico.getId_servico());

        assertThat(result)
                .isNotNull()
                .isEqualTo(new ServicoDTO(servico));
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

        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.find(servico.getId_servico())).isInstanceOf(RecursoNaoEncontradoExcessao.class);
    }
}