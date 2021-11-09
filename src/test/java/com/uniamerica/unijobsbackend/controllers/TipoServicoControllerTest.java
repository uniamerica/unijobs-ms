package com.uniamerica.unijobsbackend.controllers;

import com.uniamerica.unijobsbackend.dto.TipoServicoDTO;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import com.uniamerica.unijobsbackend.services.TipoServicoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@AutoConfigureMockMvc
//@WebMvcTest
//@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class TipoServicoControllerTest {

    @Mock
    private ServicoRepository repository;

    @Mock
    private TipoServicoRepository tipoServicoRepository;

    @Mock
    private TipoServicoService tipoServicoService;

    @InjectMocks
    private TipoServicoController TestClassController;

    @DisplayName("Trazer todos os Tipos Serviços")
    @Test
    void shouldReturnSuccess_FindAllTipoServices() {

        TipoServico tipoServico1 = new TipoServico(1,"Manutenção", "manutencao em computadores");
        tipoServicoRepository.save(tipoServico1);

        ResponseEntity<List<TipoServicoDTO>> listTipoServicos = TestClassController.listar();

        //then
        assertThat(listTipoServicos).isNotNull();
        assertThat(listTipoServicos.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @DisplayName("Registrar um novo Tipo Serviço")
    @Test
    void shouldCreateTipoServicos_ReturnSucess() {

        // cenario
        TipoServico tipoServicoteste = new TipoServico(1,"Manutenção", "manutencao em computadores");

        ResponseEntity<TipoServico> result = TestClassController.cadastrar(tipoServicoteste);

        ResponseEntity<List<TipoServicoDTO>> listTipoServicos = TestClassController.listar();

        //then
        assertThat(listTipoServicos).isNotNull();

    }

    @DisplayName("Deletar um Tipo Serviço")
    @Test
    void shouldDestroyTipoServico() {

        TipoServico tipoServicoteste = new TipoServico(1,"Manutenção", "manutencao em computadores");

        ResponseEntity<TipoServico> cad = TestClassController.cadastrar(tipoServicoteste);
        tipoServicoRepository.save(tipoServicoteste);
        TestClassController.deletar(1);

        assertThat(cad.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @DisplayName("Atualizar um Tipo Serviço")
    @Test
    void shouldUpdateTipoServico() {
        // cenario
        TipoServico tipoServicoteste = new TipoServico(1,"Manutenção", "manutencao em computadores");
        TipoServico novoTipoServico = new TipoServico("Aulas de Musica", "Aulas de Musica");
        tipoServicoRepository.save(tipoServicoteste);

        when(tipoServicoRepository.findById(any())).thenReturn(Optional.of(tipoServicoteste));

        Assertions.assertDoesNotThrow(() -> TestClassController.atualizar(novoTipoServico, 1));
    }

    @DisplayName("Busca Tipo Serviço por id")
    @Test
    void shouldaFindTipoServicoForId() {
        // cenario
        TipoServico tipoServicoteste = new TipoServico(1,"Manutenção", "manutencao em computadores");
        tipoServicoRepository.save(tipoServicoteste);

        when(tipoServicoRepository.findById(any())).thenReturn(Optional.of(tipoServicoteste));

        Assertions.assertDoesNotThrow(() -> TestClassController.servicosByTipoServicos(1));
    }
}