package com.uniamerica.unijobsbackend.controllers;

import com.uniamerica.unijobsbackend.auth.config.JwtAuthenticationEntryPoint;
import com.uniamerica.unijobsbackend.auth.config.JwtTokenUtil;
import com.uniamerica.unijobsbackend.auth.services.UserService;
import com.uniamerica.unijobsbackend.dto.ServicoDTO;
import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.services.ServicoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ServicoController.class)
class ServicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicoService service;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Test
    void findAll() throws Exception {
        Page<ServicoDTO> servicoDTO = Mockito.mock(Page.class);
        //given
        Pageable pageable = PageRequest.of(1, 10);

        when(service.findAll(pageable)).thenReturn(servicoDTO);

        String url = "/servicos";
        mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
    }

    @Test
    void find() throws Exception {
        Servico servico = Servico.builder()
                .tipoServico(new TipoServico(1))
                .ativo(true)
                .descricao("joj")
                .titulo("teste")
                .preco(10.0)
                .miniatura("ttttt")
                .build();
        ServicoDTO servicoDTO = new ServicoDTO(servico);
        when(service.find(servico.getId_servico())).thenReturn(servicoDTO);

        String url = "/servicos/{id}";
        mockMvc.perform(get(url, 1))
                .andExpect(status().isOk());

    }

    @Test
    void cadastrar() {
    }

    @Test
    void atualizar() {
    }

    @Test
    void deletar() {
    }
}