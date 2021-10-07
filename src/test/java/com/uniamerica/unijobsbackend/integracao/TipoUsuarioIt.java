package com.uniamerica.unijobsbackend.integracao;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import com.uniamerica.unijobsbackend.repositories.TipoUsuarioRepository;
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
public class TipoUsuarioIt {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    String url = "/tipo_usuarios";

    @Test
    @Order(1)
    void shouldGetAllTipoServico() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        ).andExpect(status().isOk());
    }

}
