package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.dto.ItensDTO;
import com.uniamerica.unijobsbackend.repositories.RepositorioProduto;
import com.uniamerica.unijobsbackend.repositories.RepositorioTipoProduto;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * ItensService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>set 20, 2021</pre>
 */
public class ItensServiceTest {

    @InjectMocks
    private ItensService itensService;
    @Mock
    private ServicoRepository servicoRepositoryMock;
    @Mock
    private TipoServicoRepository tipoServicoRepositoryMock;
    @Mock
    private RepositorioProduto produtoRepositoryMock;
    @Mock
    private RepositorioTipoProduto tipoProdutoRepositoryMock;

    @Mock
    private ItensDTO itensDTO;


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: recentemente_adicionados()
     */
    @Test
    public void testRecentemente_adicionados() throws Exception {
//TODO: Test goes here...


    }
}