package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.repositories.RepositorioProduto;
import com.uniamerica.unijobsbackend.repositories.RepositorioTipoProduto;
import com.uniamerica.unijobsbackend.repositories.ServicoRepository;
import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ItensService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>set 20, 2021</pre>
 */
public class ItensServiceTest {

   @Mock
   private ServicoRepository servicoRepository;
   @Mock
   private TipoServicoRepository tipoServicoRepository;
   @Mock
   private RepositorioProduto produtoRepository;
   @Mock
   private RepositorioTipoProduto tipoProdutoRepository;

  @InjectMocks
   private ItensServiceTest intesT



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