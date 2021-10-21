package com.uniamerica.unijobsbackend.integracao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniamerica.unijobsbackend.auth.config.JwtAuthenticationEntryPoint;
import com.uniamerica.unijobsbackend.auth.config.JwtTokenUtil;
import com.uniamerica.unijobsbackend.auth.services.UserService;
import com.uniamerica.unijobsbackend.dto.input.NovoServicoDTO;
import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.models.TipoUsuario;
import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
class ServicoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ServicoRepository servicoRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    String url = "/servicos";

    /*private MvcResult createUser() throws Exception {

        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setId(1);

        Usuario newUser = new Usuario(0,"willianthiagofozz@hotmail.com", "Wilian", "1234567", "501359", "45999292659", tipoUsuario);

        String content = objectMapper.writeValueAsString(newUser);
        String UserUrl = "/register";

        return mockMvc.perform(
                post(UserUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andReturn();
    }*/

    private MvcResult createTipoServico() throws Exception {

        TipoServico tipoServico = new TipoServico(0, "Manutenção", "manutenção de computadores");

        String content = objectMapper.writeValueAsString(tipoServico);
        String TipoUrl = "/tiposServicos";

        return mockMvc.perform(
                post(TipoUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andReturn();
    }

    @Test
    @Order(1)
    void shouldGetAllTipoServico() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        ).andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void shouldCreateaNewServico() throws Exception {
        MvcResult mvcResult = createTipoServico();
        TipoServico tipoServico = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), TipoServico.class);

        NovoServicoDTO servico = new NovoServicoDTO("Java", "curso de java", 500.0, "http://res.cloudinary.com/unijobs/image/upload/v1633275994/blvw1jozgdynmrtocgzx.jpg", 10, tipoServico.getId_tipo_servico(), 1);

        String content = objectMapper.writeValueAsString(servico);

        mockMvc.perform(
                post(url)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void shouldUpdateServico() throws Exception {
        shouldCreateaNewServico();

        MvcResult mvcResult = createTipoServico();
        TipoServico tipoServico = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), TipoServico.class);

        NovoServicoDTO servicoUpdate = new NovoServicoDTO("Curso de Java", "tudo sobre java", 300.0, "http://res.cloudinary.com/unijobs/image/upload/v1633275994/blvw1jozgdynmrtocgzx.jpg", 10, tipoServico.getId_tipo_servico(), 1);

        String content = objectMapper.writeValueAsString(servicoUpdate);

        mockMvc.perform(
                put(url + "/{id}", 1L)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void shouldDeleteServicoById() throws Exception {
        shouldCreateaNewServico();

        mockMvc.perform(
                MockMvcRequestBuilders.delete(url + "/{id}", 1L)
        ).andExpect(status().isOk());
    }

}