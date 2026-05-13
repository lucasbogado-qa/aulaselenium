package exselenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Exercicio1 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // O Selenium 4 já gerencia o driver automaticamente se o PATH estiver correto
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Caso de Teste 3: Login com email/senha incorretos")
    public void testLoginWithIncorrectCredentials() {
        // 3. Verificar home page
        assertTrue(driver.findElement(By.cssSelector("img[alt='Website for automation practice']")).isDisplayed());

        // 4. Click 'Signup / Login'
        driver.findElement(By.linkText("Signup / Login")).click();

        // 5. Verificar 'Login to your account'
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='Login to your account']"))).isDisplayed());

        // 6. Entrar com credenciais incorretas (Análise de Valor Limite: Email não cadastrado)
        driver.findElement(By.cssSelector("input[data-qa='login-email']")).sendKeys("invalido_2026@teste.com");
        driver.findElement(By.cssSelector("input[data-qa='login-password']")).sendKeys("senha12345");

        // 7. Click 'login'
        driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();

        // 8. Verificar erro
        WebElement errorMsg = driver.findElement(By.xpath("//p[contains(text(), 'Your email or password is incorrect!')]"));
        assertTrue(errorMsg.isDisplayed());
    }

    @Test
    @DisplayName("Caso de Teste 1: Registrar Usuário")
    public void testRegisterUser() {
        String name = "Automator Java";
        String email = "java_test_" + System.currentTimeMillis() + "@mail.com"; // Email dinâmico

        // 4. Click 'Signup / Login'
        driver.findElement(By.linkText("Signup / Login")).click();

        // 5. Verificar 'New User Signup!'
        assertTrue(driver.findElement(By.xpath("//h2[text()='New User Signup!']")).isDisplayed());

        // 6 & 7. Inserir Nome, Email e Click Signup
        driver.findElement(By.cssSelector("input[data-qa='signup-name']")).sendKeys(name);
        driver.findElement(By.cssSelector("input[data-qa='signup-email']")).sendKeys(email);
        driver.findElement(By.cssSelector("button[data-qa='signup-button']")).click();

        // 8. Verificar 'ENTER ACCOUNT INFORMATION'
        assertTrue(driver.findElement(By.xpath("//b[text()='Enter Account Information']")).isDisplayed());

        // 9. Preencher Detalhes
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys("SenhaSegura123");
        
        new Select(driver.findElement(By.id("days"))).selectByValue("15");
        new Select(driver.findElement(By.id("months"))).selectByValue("8");
        new Select(driver.findElement(By.id("years"))).selectByValue("1990");

        // 10 & 11. Checkboxes
        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();

        // 12. Endereço e Contato
        driver.findElement(By.id("first_name")).sendKeys("PrimeiroNome");
        driver.findElement(By.id("last_name")).sendKeys("UltimoNome");
        driver.findElement(By.id("address1")).sendKeys("Rua de Automação, 404");
        driver.findElement(By.id("state")).sendKeys("São Paulo");
        driver.findElement(By.id("city")).sendKeys("Campinas");
        driver.findElement(By.id("zipcode")).sendKeys("13000-000");
        driver.findElement(By.id("mobile_number")).sendKeys("11999999999");

        // 13. Click Create Account
        driver.findElement(By.cssSelector("button[data-qa='create-account']")).click();

        // 14. Verificar 'ACCOUNT CREATED!'
        assertTrue(driver.findElement(By.cssSelector("h2[data-qa='account-created']")).isDisplayed());

        // 15 & 16. Continue e Validar Usuário Logado
        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();
        
        // Em alguns casos, um anúncio pode aparecer aqui. Se o teste falhar, 
        // seria necessário um tratamento de iframe para fechar o modal.
        
        assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'Logged in as " + name + "')]")).isDisplayed());

        // 17 & 18. Deletar conta para limpar o ambiente
        driver.findElement(By.linkText("Delete Account")).click();
        assertTrue(driver.findElement(By.cssSelector("h2[data-qa='account-deleted']")).isDisplayed());
        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}