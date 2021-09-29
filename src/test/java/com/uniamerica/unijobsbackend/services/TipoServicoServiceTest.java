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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WebMvcTest
@RunWith(MockitoJUnitRunner.class)
class TipoServicoServiceTest {

    @Mock
    private ServicoRepository repository;
    @Mock
    private TipoServicoRepository tipoServicoRepository;

    @Autowired
    private TipoServicoService tipoServicoClass;

    @BeforeEach
    void setUp() {
        tipoServicoClass = new TipoServicoService(tipoServicoRepository, repository);
    }

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
        TipoServico tipoServico = new TipoServico(1,"Manutenção", "manutencao em computadores");
        tipoServicoRepository.save(tipoServico);

        // simulação
        Mockito.when(tipoServicoRepository.findTipoServicoByNome(tipoServico.getNome())).thenReturn(Optional.of(tipoServico));
        var result = tipoServicoClass.novoTipoServico(tipoServico);

        assertThat(result.getId_tipo_servico()).isEqualTo(1);
        assertThat(result).isNotNull();
        assertThat(result.getDescricao()).isEqualTo("manutencao em computadores");

    }

    @DisplayName("Deletar um Tipo Serviço")
    @Test
    void shouldDestroyTipoServico() {
        // cenario
        TipoServico tipoServico = new TipoServico(1,"Manutenção", "manutencao em computadores");
        tipoServicoRepository.save(tipoServico);

        var result = tipoServicoClass.deletarTipoServico(1);
        assertThat(result).isEqualTo("Tipo Serviço deletado com sucesso!");

    }
}