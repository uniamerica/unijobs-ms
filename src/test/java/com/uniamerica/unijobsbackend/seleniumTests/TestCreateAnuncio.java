package com.uniamerica.unijobsbackend.seleniumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class TestCreateAnuncio {

    private WebDriver driver;

    private String retornaEmailAleatorio(){
        Random random = new Random();
        int numeroInteiroAleatorio_0_a_1000 = random.nextInt(1000);
        return "WilzinhoTestes" + numeroInteiroAleatorio_0_a_1000 + "@hotmail.com";
    }

    private Integer retornaRAAleatorio(){
        Random random = new Random();
        return random.nextInt((999999 - 100000) + 1) + 100000;
    }

    @Before
    public void setUp() throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\SUPORTE\\Documents\\workspace\\JAVA\\UNIJOBS\\images\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() throws Exception{
        driver.quit();
    }

    /*TESTE PARA ABRIR NAVEGADOR E VERIFICAR SE O SITE ABRE*/

    @Test
    public void openWebSite() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(3000);
        assertTrue("Título da Página difere do esperado", driver.getTitle().contentEquals("UniJobs"));
    }

    /*TESTE PARA TENTAR CRIAR UM NOVO PRODUTO SEM TER UMA CONTA NO SISTEMA*/

    @Test
    public void TryCreateANewProductWithoutLogin() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(2000);
        try{
            driver.findElement(By.xpath("/html/body/div/header/div/a[1]")).click();
            Thread.sleep(2000);
            assertTrue("Página difere do esperado", driver.getCurrentUrl().contentEquals("http://localhost:3000/login"));
        }
        finally {
            driver.quit();
        }
    }

    /*TESTE REGISTRAR UM NOVO USÁRIO*/

    @Test
    public void RegisterANewUser() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(2000);
        String emailAleatorio = retornaEmailAleatorio();
        String raAleatorio = Integer.toString(retornaRAAleatorio());
        try{
            driver.findElement(By.xpath("/html/body/div/header/div/a[2]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div/section/section/section/a[2]")).click();
            Thread.sleep(2000);
            driver.findElement(By.name("nome")).sendKeys("Willian Thiago");
            driver.findElement(By.name("email")).sendKeys(emailAleatorio);
            driver.findElement(By.name("celular")).sendKeys("45999299999");
            driver.findElement(By.name("ra")).sendKeys(raAleatorio);
            driver.findElement(By.name("senha")).sendKeys("123456");
            driver.findElement(By.name("senha_confirmacao")).sendKeys("123456");
            driver.findElement(By.xpath("/html/body/div/section/section/form/button")).click();
            Thread.sleep(2000);
            assertTrue("Página difere do esperado", driver.getCurrentUrl().contentEquals("http://localhost:3000/"));
        }
        finally {
            driver.quit();
        }
    }

    /*TESTE PARA LOGAR UM USUÁRIO EXISTENTE*/

    @Test
    public void LoginANewUser() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(2000);
        try{
            driver.findElement(By.xpath("/html/body/div/header/div/a[2]")).click();
            Thread.sleep(3000);
            driver.findElement(By.name("email")).sendKeys("wil@gmail.com");
            driver.findElement(By.name("password")).sendKeys("123456");
            driver.findElement(By.xpath("/html/body/div/section/section/form/button")).click();
            Thread.sleep(3000);
            assertTrue("Página difere do esperado", driver.getCurrentUrl().contentEquals("http://localhost:3000/"));
        }
        finally {
            driver.quit();
        }
    }

    /*TESTE PARA CADASTRAR UM NOVO PRODUTO NO SISTEMA*/

    @Test
    public void createANewProduct() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(2000);
        try{
            // Fazer o login na Plataforma
            driver.findElement(By.xpath("/html/body/div/header/div/a[2]")).click();
            Thread.sleep(3000);
            driver.findElement(By.name("email")).sendKeys("wil@gmail.com");
            driver.findElement(By.name("password")).sendKeys("123456");
            driver.findElement(By.xpath("/html/body/div/section/section/form/button")).click();
            Thread.sleep(3000);

            // Criar um produto
            driver.findElement(By.xpath("/html/body/div/header/div/a[1]")).click();
            Thread.sleep(3000);
            driver.findElement(By.name("titulo")).sendKeys("Bolinhos de Chocolate");
            driver.findElement(By.name("descricao")).sendKeys("Deguste de deliciosos bolinhos de chocolate, com bastante chocolate, e mais chocolate, para fazer bem pro seu colesterol");
            driver.findElement(By.name("prazo")).sendKeys("1");
            driver.findElement(By.name("preco")).sendKeys("500");
            driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/form/div[5]/div/div/div")).click();
            driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/form/div[5]/div/div/div/div[1]/div[2]/div/input")).sendKeys("alimentos" + Keys.ENTER);
            driver.findElement(By.name("miniatura")).sendKeys("C:\\Users\\SUPORTE\\Documents\\workspace\\JAVA\\UNIJOBS\\images\\amindus-784.jpg");
            driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/form/button/button[1]")).click();
            Thread.sleep(6000);
            assertTrue("Página difere do esperado", driver.getCurrentUrl().contentEquals("http://localhost:3000/"));
        }
        finally {
            driver.quit();
        }
    }

    /*TESTE PARA ACESSAR UM PRODUTO DA LANDING PAGE*/

    @Test
    public void AccessProductLandingPage() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(2000);
        try{
            driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/div[2]/ul/li[23]/section/section/footer/a")).click();
            Thread.sleep(4000);

            assertEquals("Bolinhos de Chocolate", driver.findElement(By.xpath("/html/body/div/section/div/div[2]/h1")).getText());
            assertEquals("Deguste de deliciosos bolinhos de chocolate, com bastante chocolate, e mais chocolate, para fazer bem pro seu colesterol",
                    driver.findElement(By.xpath("/html/body/div/section/div/div[3]/p")).getText());
        }
        finally {
            driver.quit();
        }
    }

    /*TESTE PARA FILTRAR APENAS OS PRODUTOS E ENTRAR EM UM PRODUTO*/

    @Test
    public void FilterProductTest() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(2000);
        try{
            driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div/a")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div/div[1]/div[1]")).click();
            Thread.sleep(2000);

            assertEquals("roda", driver.findElement(By.xpath("/html/body/div/section/div/div[2]/h1")).getText());
            assertEquals("para andar", driver.findElement(By.xpath("/html/body/div/section/div/div[3]/p")).getText());
        }
        finally {
            driver.quit();
        }
    }

    /*TESTE PARA FILTRAR APENAS OS SERVIÇOS E ENTRAR EM UM SERVIÇO*/

    @Test
    public void FilterServicosTest() throws InterruptedException {
        driver.get("http://localhost:3000/");
        Thread.sleep(2000);
        try{
            driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div/a")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body/div/div[1]/div[1]")).click();
            Thread.sleep(2000);

            assertEquals("python", driver.findElement(By.xpath("/html/body/div/section/div/div[2]/h1")).getText());
            assertEquals("curso de piton", driver.findElement(By.xpath("/html/body/div/section/div/div[3]/p")).getText());
        }
        finally {
            driver.quit();
        }
    }

}