package com.uniamerica.unijobsbackend.selenuim;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class UsuarioSeleniumTest {

    private WebDriver driver;

    private String retornaEmailAleatorio(){
        Random random = new Random();
        int numeroInteiroAleatorio_0_a_1000 = random.nextInt(1000);
        return "lioTestes" + numeroInteiroAleatorio_0_a_1000 + "@unijobs.com";
    }

    private Integer retornaRAAleatorio(){
        Random random = new Random();
        return random.nextInt((999999 - 100000) + 1) + 100000;
    }


    @Before
    public void setUp() throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ALUNO-43\\Documents\\unijobs-backend\\src\\test\\java\\com\\uniamerica\\unijobsbackend\\selenuim\\chromedriver.exe");
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
            driver.findElement(By.name("nome")).sendKeys("lio croons");
            driver.findElement(By.name("email")).sendKeys(emailAleatorio);
            driver.findElement(By.name("celular")).sendKeys("45999299999");
            driver.findElement(By.name("ra")).sendKeys(raAleatorio);
            driver.findElement(By.name("senha")).sendKeys("1234");
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
            driver.findElement(By.name("email")).sendKeys("lio@unijobs.com");
            driver.findElement(By.name("password")).sendKeys("1234");
            driver.findElement(By.xpath("/html/body/div/section/section/form/button")).click();
            Thread.sleep(3000);

            // Criar um produto
            driver.findElement(By.xpath("/html/body/div/header/div/a[1]")).click();
            Thread.sleep(3000);
            driver.findElement(By.name("titulo")).sendKeys("NOTEBOOK GAMER ALIENWARE X17");
            driver.findElement(By.name("descricao")).sendKeys("Intel® Core™ i9-11980HK (11ª geração, 8-Core, cache L3 de 24MB, até 5.0GHz com Tecnologia Turbo Boost Max 3.0)");
            driver.findElement(By.name("prazo")).sendKeys("1");
            driver.findElement(By.name("preco")).sendKeys("3500");
            driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/form/div[5]/div/div/div")).click();
            driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/form/div[5]/div/div/div/div[1]/div[2]/div/input")).sendKeys("alimentos" + Keys.ENTER);
            driver.findElement(By.name("miniatura")).sendKeys("C:\\Users\\ALUNO-43\\Documents\\unijobs-backend\\src\\test\\java\\com\\uniamerica\\unijobsbackend\\selenuim\\Assert\\dell.jpg");
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

            assertEquals("Placa de vídeo - nvidia Quadro rtx 8000 (48GB / pci-e)", driver.findElement(By.xpath("/html/body/div/section/div/div[2]/h1")).getText());
            assertEquals("\n" +
                            "NVIDIA · Quadro · 48 GB · GDDR · DisplayPor",
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
