package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.Excessoes.RecursoNaoEncontradoExcessao;
import com.uniamerica.unijobsbackend.dto.ServicoDTO;
import com.uniamerica.unijobsbackend.dto.TipoServicoDTO;
import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

//@AutoConfigureMockMvc
//@WebMvcTest
//@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class TipoServicoServiceTest {

    @Mock
    private ServicoRepository repository;
    @Mock
    private TipoServicoRepository tipoServicoRepository;

    @InjectMocks
    private TipoServicoService tipoServicoClass;

    @BeforeEach
    void setUp() {
        tipoServicoClass = new TipoServicoService(tipoServicoRepository, repository);
    }

    @DisplayName("Trazer todos os Tipos Serviços")
    @Test
    void shouldReturnSuccess_FindAllTipoServices() {

        TipoServico tipoServico1 = new TipoServico(1,"Manutenção", "manutencao em computadores");
        tipoServicoRepository.save(tipoServico1);

        var listTipoServicos = tipoServicoClass.listarTiposServicos();

        //then
        assertThat(listTipoServicos.contains(tipoServico1));
    }

    @DisplayName("Registrar um novo Tipo Serviço")
    @Test
    void shouldCreateTipoServicos_ReturnSucess() {

        // cenario
        TipoServico tipoServicoteste = new TipoServico(1,"Manutenção", "manutencao em computadores");
        var result = tipoServicoClass.novoTipoServico(tipoServicoteste);

        var listTipoServicos = tipoServicoClass.listarTiposServicos();

        //then
        assertThat(listTipoServicos.contains(tipoServicoteste));

    }

    @DisplayName("Deletar um Tipo Serviço")
    @Test
    void shouldDestroyTipoServico() {
        // cenario
        TipoServico tipoServicoteste = new TipoServico(1,"Manutenção", "manutencao em computadores");

        when(tipoServicoClass.novoTipoServico(tipoServicoteste)).thenReturn(tipoServicoteste);
        var result = tipoServicoClass.deletarTipoServico(1);

        assertThat(result).isEqualTo("Tipo Serviço deletado com sucesso!");

    }

    @DisplayName("Atualizar um Tipo Serviço")
    @Test
    void shouldUpdateTipoServico() {
        // cenario
        TipoServico tipoServicoteste = new TipoServico(1,"Manutenção", "manutencao em computadores");
        TipoServico novoTipoServico = new TipoServico("Aulas de Musica", "Aulas de Musica");
        tipoServicoRepository.save(tipoServicoteste);

        when(tipoServicoRepository.findById(any())).thenReturn(Optional.of(tipoServicoteste));

        Assertions.assertDoesNotThrow(() -> tipoServicoClass.atualizarTipoServico(1, novoTipoServico));
    }

    @DisplayName("Busca Tipo Serviço por id")
    @Test
    void shouldaFindTipoServicoForId() {
        // cenario
        TipoServico tipoServicoteste = new TipoServico(1,"Manutenção", "manutencao em computadores");
        tipoServicoRepository.save(tipoServicoteste);

        when(tipoServicoRepository.findById(any())).thenReturn(Optional.of(tipoServicoteste));

        Assertions.assertDoesNotThrow(() -> tipoServicoClass.servicosByTipoServicos(1));
    }
}