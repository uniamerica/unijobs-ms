package com.uniamerica.unijobsbackend.integracao;


import com.uniamerica.unijobsbackend.models.TipoUsuario;
import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.repositories.TipoUsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;





@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TipoUsuarioEndPoint {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static  class Config{
        @Bean
        public RestTemplateBuilder restTemplateBuilder(){
            return new RestTemplateBuilder().basicAuthentication("liocroons", "1234");

        }
    }

    // problema com assertThat
//    @Test
//    public void listUsuariosWhenUserAndPassawordAreShouldReturnStatusCode401(){
//
//        System.out.println(port);
//        restTemplate.withBasicAuth("1", "1");
//        ResponseEntity<String> response = restTemplate.getForEntity("/tipo_usuarios", String.class);
//        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);}
//
//

//@Test
//    public void getByIdUsuariosWhenUserAndPassawordAreIncorrectShouldReturnStatusCode401(){
//
//        System.out.println(port);
//        restTemplate.withBasicAuth("1", "1");
//        ResponseEntity<String> response = restTemplate.getForEntity("/tipo_usuarios/1", String.class);
//        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
//}

@Test
public void getByIdUsuariosWhenUserAndPassawordArecorrectShouldReturnStatusCode200(){

    List<TipoUsuario> Tipo_usuarios = asList(new TipoUsuario(1,"admin1"),
        new TipoUsuario(2,"Admin2"));
        BDDMockito.when(tipoUsuarioRepository.findAll()).thenReturn(Tipo_usuarios);
        ResponseEntity<String> response = restTemplate.getForEntity("/tipo_usuarios/1", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
}
}
