package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.dto.TipoServicoDTO;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TipoServicoServiceTest {

    @Autowired
    @Mock
    private ServicoRepository repository;

    @Autowired
    @Mock
    private TipoServicoRepository tipoServicoRepository;

    @Autowired
    @Mock
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

        var listTipoServicos = assertDoesNotThrow(() -> tipoServicoClass.findAll());

        assertFalse(listTipoServicos.isEmpty());
    }

    @DisplayName("Registrar um novo Tipo Serviço")
    @Test
    void shouldCreateTipoServicos_ReturnSucess() {
        TipoServico tipoServicoteste = new TipoServico(null, "Manutenção", "manutencao em computadores");
        var result = tipoServicoClass.save(tipoServicoteste);
        tipoServicoteste.setId_tipo_servico(result.getId_tipo_servico());

        var listTipoServicos = tipoServicoClass.findAll();
        TipoServicoDTO tipoServicoDTO = listTipoServicos.iterator().next();

        assertEquals(tipoServicoDTO.getId_tipo_servico(), result.getId_tipo_servico());
    }

    @DisplayName("Deletar um Tipo Serviço")
    @Test
    void shouldDestroyTipoServico() {
        TipoServico tipoServicoteste = new TipoServico(null, "Manutenção", "manutencao em computadores");
        TipoServico savedTipoServico = tipoServicoClass.save(tipoServicoteste);

        var result = tipoServicoClass.deletarTipoServico(savedTipoServico.getId_tipo_servico());

        assertThat(result).isEqualTo("Tipo Serviço deletado com sucesso!");

    }

    @DisplayName("Atualizar um Tipo Serviço")
    @Test
    void shouldUpdateTipoServico() {
        TipoServico tipoServicoteste = new TipoServico(1, "Manutenção", "manutencao em computadores");
        TipoServico novoTipoServico = new TipoServico("Aulas de Musica", "Aulas de Musica");
        tipoServicoRepository.save(tipoServicoteste);

        assertDoesNotThrow(() -> tipoServicoClass.update(1, novoTipoServico));
    }

    @DisplayName("Busca Tipo Serviço por id")
    @Test
    void shouldaFindTipoServicoForId() {
        TipoServico tipoServicoteste = new TipoServico(null, "Manutenção", "manutencao em computadores");
        TipoServico saved = tipoServicoRepository.save(tipoServicoteste);

        assertDoesNotThrow(() -> tipoServicoClass.findByServiceType(saved.getId_tipo_servico()));
    }
}