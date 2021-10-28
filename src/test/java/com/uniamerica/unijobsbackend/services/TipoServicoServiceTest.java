package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

        TipoServico tipoServico1 = new TipoServico(1, "Manutenção", "manutencao em computadores");
        tipoServicoRepository.save(tipoServico1);

        var listTipoServicos = tipoServicoClass.findAll();

        assertTrue(listTipoServicos.contains(tipoServico1));
    }

    @DisplayName("Registrar um novo Tipo Serviço")
    @Test
    void shouldCreateTipoServicos_ReturnSucess() {

        TipoServico tipoServicoteste = new TipoServico(1, "Manutenção", "manutencao em computadores");
        var result = tipoServicoClass.save(tipoServicoteste);

        var listTipoServicos = tipoServicoClass.findAll();

        assertTrue(listTipoServicos.contains(tipoServicoteste));

    }

    @DisplayName("Deletar um Tipo Serviço")
    @Test
    void shouldDestroyTipoServico() {
        TipoServico tipoServicoteste = new TipoServico(1, "Manutenção", "manutencao em computadores");

        when(tipoServicoClass.save(tipoServicoteste)).thenReturn(tipoServicoteste);
        var result = tipoServicoClass.deletarTipoServico(1);

        assertThat(result).isEqualTo("Tipo Serviço deletado com sucesso!");

    }

    @DisplayName("Atualizar um Tipo Serviço")
    @Test
    void shouldUpdateTipoServico() {
        TipoServico tipoServicoteste = new TipoServico(1, "Manutenção", "manutencao em computadores");
        TipoServico novoTipoServico = new TipoServico("Aulas de Musica", "Aulas de Musica");
        tipoServicoRepository.save(tipoServicoteste);

        when(tipoServicoRepository.findById(any())).thenReturn(Optional.of(tipoServicoteste));

        assertDoesNotThrow(() -> tipoServicoClass.update(1, novoTipoServico));
    }

    @DisplayName("Busca Tipo Serviço por id")
    @Test
    void shouldaFindTipoServicoForId() {
        TipoServico tipoServicoteste = new TipoServico(1, "Manutenção", "manutencao em computadores");
        tipoServicoRepository.save(tipoServicoteste);

        when(tipoServicoRepository.findById(any())).thenReturn(Optional.of(tipoServicoteste));

        assertDoesNotThrow(() -> tipoServicoClass.findByServiceType(1));
    }
}