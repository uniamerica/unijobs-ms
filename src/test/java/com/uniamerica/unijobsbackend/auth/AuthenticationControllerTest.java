package com.uniamerica.unijobsbackend.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniamerica.unijobsbackend.auth.dto.LoginDto;
import com.uniamerica.unijobsbackend.auth.dto.ResponseTokenDto;
import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        usuarioRepository.save(Usuario.builder()
                .nome("admin")
                .email("admin")
                .senha(new BCryptPasswordEncoder().encode("admin"))
                .build());
    }

    @AfterEach
    void tearDown() {
        usuarioRepository.deleteAll();
    }

    @Test
    void shouldLogin() throws Exception {
        LoginDto loginDto = new LoginDto("admin", "admin");
        mockMvc.perform(postAuthentication(loginDto))
                .andExpect(ResultMatcher.matchAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.access_token", allOf(notNullValue(), isA(String.class))),
                        jsonPath("$.refresh_token", allOf(notNullValue(), isA(String.class)))
                ));
    }

    @Test
    void shouldUseRefreshToken() throws Exception {
        LoginDto loginDto = new LoginDto("admin", "admin");
        ResponseTokenDto tokenDto = objectMapper.readValue(mockMvc.perform(postAuthentication(loginDto))
                .andReturn()
                .getResponse()
                .getContentAsByteArray(), ResponseTokenDto.class);


        mockMvc.perform(post("/refresh_token")
                .contentType(MediaType.APPLICATION_JSON)
                .param("refresh_token", tokenDto.getRefresh_token()))
                .andExpect(ResultMatcher.matchAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.access_token", allOf(notNullValue(), isA(String.class))),
                        jsonPath("$.refresh_token", allOf(notNullValue(), isA(String.class)))
                ));

    }

    @SneakyThrows
    private MockHttpServletRequestBuilder postAuthentication(LoginDto loginDto) {
        return post("/authenticate")
                .content(objectMapper.writeValueAsString(loginDto))
                .contentType(MediaType.APPLICATION_JSON);
    }


}
