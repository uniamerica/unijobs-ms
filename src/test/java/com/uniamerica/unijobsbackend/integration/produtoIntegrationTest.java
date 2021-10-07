package com.uniamerica.unijobsbackend.integration;

import com.google.gson.Gson;
import com.uniamerica.unijobsbackend.auth.services.AuthService;
import com.uniamerica.unijobsbackend.controllers.ProdutoController;
import com.uniamerica.unijobsbackend.controllers.TipoProdutoController;
import com.uniamerica.unijobsbackend.controllers.UsuarioController;
import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.models.TipoProduto;
import com.uniamerica.unijobsbackend.models.TipoUsuario;
import com.uniamerica.unijobsbackend.models.Usuario;
import com.uniamerica.unijobsbackend.repositories.RepositorioProduto;
import com.uniamerica.unijobsbackend.repositories.RepositorioTipoProduto;
import com.uniamerica.unijobsbackend.repositories.TipoUsuarioRepository;
import com.uniamerica.unijobsbackend.repositories.UsuarioRepository;
import com.uniamerica.unijobsbackend.services.ProdutoService;
import com.uniamerica.unijobsbackend.services.TipoProdutoService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class produtoIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProdutoController controllerProduto;

    @Autowired
    private TipoProdutoController controllerTipoProduto;

    @Autowired
    private UsuarioController controllerUsuario;

    @Autowired
    private ProdutoService servicoProduto;

    @Autowired
    private TipoProdutoService servicoTipoProduto;

    @Autowired
    private RepositorioProduto repositorioProduto;

    @Autowired
    private RepositorioTipoProduto repositorioTipoProduto;

    @Autowired
    private UsuarioRepository repositorioUsuario;

    @Test
    public void CadastrarUsuarioCadastrarProdutoDeUsuarioVerificarPersistencia()
            throws Exception {
        JSONObject user= new JSONObject();
        user.put("email", "brayan@unijobs.com");
        user.put("password", "1234");
        TipoProduto t1 = new TipoProduto(1,"alimenticio", "Todos os tipos de comida");
        Usuario u1 = new Usuario(1,"teste@email.com", "senha123", "nome123", "45998260289", "404040", null);
        Produto p1 = new Produto(0, "teste", "teste", 12.0, "teste", false, 28, t1, u1);

        MvcResult resultado2 = (MvcResult) mvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(("{\"email\":\"b@unijobs.com\",\"senha\":\"1234\",\"nome\":\"brayan\",\"celular\":\"4598403335\",\"ra\":\"111111\"}")))
                .andExpect(status().isOk());

        ///MvcResult resultado = (MvcResult) mvc.perform(post("/authenticate")
        ///        .contentType(MediaType.APPLICATION_JSON)
        ///        .content("{\"email\":\"b@unijobs.com\",\"password\":\"1234\"}"));

        ///String token = resultado.getResponse().getContentAsString();
        ///System.out.println(token);
        ///mvc.perform(get("/tipos_produtos").contentType(MediaType.APPLICATION_JSON).content(String.valueOf(u1))).andExpect(status().isOk());
    }
}
