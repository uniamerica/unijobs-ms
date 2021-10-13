package com.uniamerica.unijobsbackend.integracao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TipoServicoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TipoServicoRepository tipoServicoRepository;

    String url = "/tiposServicos";

    @Test
    @Order(1)
    void shouldGetAllTipoServico() throws Exception {
        String url = "/tiposServicos";
        mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        ).andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void shouldCreateaNewTipoServico() throws Exception {

        TipoServico tipoServicoTest = new TipoServico("Manutenção", "Manutenção de computadores");

        String content = objectMapper.writeValueAsString(tipoServicoTest);

        mockMvc.perform(
                post(url)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void shouldAddTipoServicoReturnValidationExceptionIfNotValid() throws Exception {
        TipoServico tipoServicoTest = new TipoServico("Manutenção");

        String content = objectMapper.writeValueAsString(tipoServicoTest);

        mockMvc.perform(
                post(url)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    @Order(4)
    void shouldUpdateTipoServico() throws Exception {
        shouldCreateaNewTipoServico();

        TipoServico tipoServicoTest = new TipoServico("Manutenção", "Manutenção de computadores");

        String content = objectMapper.writeValueAsString(tipoServicoTest);

        mockMvc.perform(
                put(url + "/{id}", 1L)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void shouldUpdateTipoServicoReturnsExceptionIfNotValid() throws Exception {
        TipoServico tipoServicoTest = new TipoServico("Manutenção", "Manutenção de computadores");

        String content = objectMapper.writeValueAsString(tipoServicoTest);

        mockMvc.perform(
                put(url + "/{id}", 1L)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    @Order(6)
    void shouldDeleteById() throws Exception {
        shouldCreateaNewTipoServico();

        mockMvc.perform(
                MockMvcRequestBuilders.delete(url + "/{id}", 1L)
        ).andExpect(status().isOk());
    }
}