package com.uniamerica.unijobsbackend.controllers;

import com.uniamerica.unijobsbackend.auth.config.JwtAuthenticationEntryPoint;
import com.uniamerica.unijobsbackend.auth.config.JwtTokenUtil;
import com.uniamerica.unijobsbackend.auth.services.UserService;
import com.uniamerica.unijobsbackend.dto.ServicoDTO;
import com.uniamerica.unijobsbackend.models.Servico;
import com.uniamerica.unijobsbackend.models.TipoServico;
import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.services.ServicoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServicoController.class)
@ExtendWith(SpringExtension.class)
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

    @InjectMocks
    private ServicoController servicoController;

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
        TipoServico tiposervico = new TipoServico(1);
        Usuario usuario = new Usuario(1);

        Servico newServico = new Servico(
                1,
                "Python",
                "Aulas de Python",
                100.0,
                "http://res.cloudinary.com/unijobs/image/upload/v1633275994/blvw1jozgdynmrtocgzx.jpg",
                false,
                10,
                tiposervico,
                usuario
        );

        ServicoDTO newServicoDTO = new ServicoDTO(newServico);

        when(service.find(newServico.getId_servico())).thenReturn(newServicoDTO);

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